package com.mattindustries.app.security.extractors;

import com.mattindustries.app.model.UserDto;
import com.mattindustries.app.services.LoginService;
import com.mattindustries.app.utils.ApplicationContextProvider;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

public class FacebookPrincipalExtractor implements PrincipalExtractor {

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String id = (String) map.get("id");
        LoginService loginService = ApplicationContextProvider.getBean("loginService", LoginService.class);
        UserDto user = loginService.getMe(id);
        if (user == null) {
            return new UserDto();
        } else {
            return user;
        }
    }
}
