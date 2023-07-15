/*
package com.helf.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.helf.dto.Errors;
import com.helf.dto.SignupRequest;
import com.helf.entity.Role;
import com.helf.entity.Users;
import com.helf.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.helf.dto.Constants.*;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private ObjectMapper mapper;

   @Autowired
    private ModelMapper modelMapper;
    public ResponseEntity registerUser(SignupRequest signUpRequest) throws JsonProcessingException {
        try {

            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(USER_NAME_EXIST);
            }

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(EMAIL_EXIST);
            }
            Users users = new Users();
            users.setEmail(signUpRequest.getEmail());
            users.setUsername(signUpRequest.getUsername());
            users.setPassword(encoder.encode(signUpRequest.getPassword()));
            Set<Role> roles = mapper.readValue(new Gson().toJson(signUpRequest.getRole()), new TypeReference<Set<Role>>() {
            });
            users.setRoles(roles);
            userRepository.save(users);
           // SignupRequest signupRequest = modelMapper.map(users, SignupRequest.class);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(SIGNUP_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
*/
