package com.redjen.softcamp.social;

import java.util.Map;
import java.util.UUID;

import com.redjen.softcamp.model.SocialProvider;
import com.redjen.softcamp.model.UserEntity;
import com.redjen.softcamp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public abstract class BasePrincipalExtractor implements PrincipalExtractor {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String[] PRINCIPAL_KEYS = new String[] { "user", "username", "userid", "user_id", "login",
            "id", "name" };

    @Transactional
    public void saveSocialUser(String id, SocialProvider provider) {
        UserEntity userEntity = userRepository.findByUsernameAndSns(id, provider);

        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setUsername(id);
            userEntity.setSns(provider);
            userEntity.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
        }

        userRepository.save(userEntity);
    }

    protected Object createPrincipal(Map<String, Object> map) {
        for (String key : PRINCIPAL_KEYS) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }
        return null;
    }
}
