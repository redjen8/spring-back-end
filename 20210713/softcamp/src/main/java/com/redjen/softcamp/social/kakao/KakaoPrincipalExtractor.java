package com.redjen.softcamp.social.kakao;

import java.util.Map;

import com.redjen.softcamp.social.BasePrincipalExtractor;
import com.redjen.softcamp.model.SocialProvider;

import org.springframework.stereotype.Component;

@Component
public class KakaoPrincipalExtractor extends BasePrincipalExtractor {

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String id = map.get("id").toString();
        this.saveSocialUser(id, SocialProvider.KAKAO);
        return this.createPrincipal(map);
    }
}
