package com.redjen.softcamp.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import com.redjen.softcamp.service.CustomAccessDeniedHandler;
import com.redjen.softcamp.service.LoginSuccessHandler;
import com.redjen.softcamp.service.UserDetailsServiceImpl;
import com.redjen.softcamp.social.facebook.FacebookPrincipalExtractor;
import com.redjen.softcamp.social.github.GithubPrincipalExtractor;
import com.redjen.softcamp.social.google.GooglePrincipalExtractor;
import com.redjen.softcamp.social.kakao.KakaoPrincipalExtractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private OAuth2ClientContext auth2ClientContext;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private FacebookPrincipalExtractor facebookPrincipalExtractor;

    @Autowired
    private KakaoPrincipalExtractor kakaoPrincipalExtractor;

    @Autowired
    private GithubPrincipalExtractor githubPrincipalExtractor;

    @Autowired
    private GooglePrincipalExtractor googlePrincipalExtractor;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("1234")).roles("USER");
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css", "/js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .mvcMatchers("/login/**", "/logout/**", "/private/**", "/admin/**", "/", "/profile/**", "/my-login")
                .and().authorizeRequests().antMatchers("/private/**").hasAnyRole("USER").antMatchers("/admin/**")
                .hasAnyRole("ADMIN").and().sessionManagement().maximumSessions(1).expiredUrl("/login?expire=true").and()
                .and().rememberMe().alwaysRemember(false).rememberMeParameter("remember-me").and().formLogin()
                .loginPage("/login").loginProcessingUrl("/login").usernameParameter("username")
                .passwordParameter("password").failureUrl("/login?error=true").successHandler(loginSuccessHandler).and()
                .logout().logoutUrl("/logout").deleteCookies("JSESSIONID").clearAuthentication(true)
                .invalidateHttpSession(true).and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)

                .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    private Filter ssoFilter(ClientResources client, String path, PrincipalExtractor extractor) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);

        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), auth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
                client.getClient().getClientId());
        tokenServices.setRestTemplate(template);
        tokenServices.setPrincipalExtractor(extractor);
        filter.setTokenServices(tokenServices);
        return filter;
    }

    @Bean
    public OAuth2ClientContext auth2ClientContext() {
        return new DefaultOAuth2ClientContext();
    }

    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(kakao(), "/login/kakao", kakaoPrincipalExtractor));
        filters.add(ssoFilter(facebook(), "/login/facebook", facebookPrincipalExtractor));
        filters.add(ssoFilter(github(), "/login/github", githubPrincipalExtractor));
        filters.add(ssoFilter(google(), "/login/google", googlePrincipalExtractor));
        filter.setFilters(filters);
        return filter;
    }

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("kakao")
    public ClientResources kakao() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("google")
    public ClientResources google() {
        return new ClientResources();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
