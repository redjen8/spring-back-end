package com.redjen.softcamp.social.github;

import java.util.Map;

import com.redjen.softcamp.model.SocialProvider;
import com.redjen.softcamp.social.BasePrincipalExtractor;

import org.springframework.stereotype.Component;

@Component
public class GithubPrincipalExtractor extends BasePrincipalExtractor {

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String id = map.get("id").toString();
        this.saveSocialUser(id, SocialProvider.GITHUB);
        return this.createPrincipal(map);
    }
}
