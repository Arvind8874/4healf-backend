/*

package com.helf.security;




import com.helf.dto.DoctorRequest;

import com.helf.entity.SecureUser;
import com.helf.entity.enums.Status;

import com.helf.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomAuthentcationProvider implements AuthenticationProvider {

    @Autowired
   DoctorService doctorService;

    @Autowired
    private MD5EncryptionWithSecretKey md5EncryptionWithSecretKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        DoctorRequest user;
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
//        if (userName.contains("@") && userName.contains(".")) {
//            user = userService.getUserByEmailId(userName);
//            if (user.getStatus().equalsIgnoreCase("INACTIVE")){
//                throw new OAuth2Exception("User is InActive");
//            }
//        } else {
//        if ()
            user = doctorService.getUserByUsername(userName);
            if (user.getStatus().equals(Status.INACTIVE)){
                throw new OAuth2Exception("User is InActive");
            }
//        }
        if (user == null) {
            throw new OAuth2Exception("Invalid login credentials");
        }

        String encryptedPassword= MD5EncryptionWithSecretKey.encrypt(password);

        if (encryptedPassword.equals(user.getPassword())) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            SecureUser secureUser = new SecureUser(user);
            UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(secureUser, password,
                    grantedAuths);
            return usernamePasswordAuthenticationToken;
        }else {
            throw new OAuth2Exception("Invalid login credentials");
        }
     // return null;
    }
    @Override
    public boolean supports(final Class<?> authentication) {
        // TODO Auto-generated method stub
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }



}



*/
