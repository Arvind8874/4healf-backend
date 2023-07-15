package com.helf.service;

import com.helf.entity.SecureUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CustomTokenEnricher extends TokenEnhancerChain {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken token, OAuth2Authentication authentication) {
        // TODO Auto-generated method stub
        final DefaultOAuth2AccessToken enhanceToken = (DefaultOAuth2AccessToken) token;
        SecureUser superUser = (SecureUser) authentication.getUserAuthentication().getPrincipal();
        final Map<String, Object> userInfo = new LinkedHashMap<String, Object>();
        userInfo.put("user",superUser.getUser());
        enhanceToken.setAdditionalInformation(userInfo);
        return super.enhance(token, authentication);
    }
}