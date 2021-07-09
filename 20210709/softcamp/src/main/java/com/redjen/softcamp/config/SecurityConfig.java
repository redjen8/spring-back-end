package com.redjen.softcamp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import com.redjen.softcamp.common.security.CustomAccessDeniedHandler;
import com.redjen.softcamp.common.security.CustomLoginSuccessHandler;
import com.redjen.softcamp.common.security.CustomUserDetailsService;

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        DataSource dataSource;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                log.info("Security config..");

                http.authorizeRequests().antMatchers("/board/list").permitAll();

                http.authorizeRequests().antMatchers("/board/register").hasRole("MEMBER");

                http.authorizeRequests().antMatchers("/notice/register").permitAll();

                http.authorizeRequests().antMatchers("/notice/register").hasRole("ADMIN");

                http.formLogin().loginPage("/login").successHandler(authenticationSuccessHandler());

                http.logout().logoutUrl("/logout").invalidateHttpSession(true);

                http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        }

        @Bean
        public UserDetailsService userDetailsService() {
                return new CustomUserDetailsService();
        }

        @Bean
        public AccessDeniedHandler accessDeniedHandler() {
                return new CustomAccessDeniedHandler();
        }

        @Bean
        public AuthenticationSuccessHandler authenticationSuccessHandler() {
                return new CustomLoginSuccessHandler();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
