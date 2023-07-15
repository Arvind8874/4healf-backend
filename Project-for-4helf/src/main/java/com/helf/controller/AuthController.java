/*
package com.helf.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.helf.dto.JwtResponse;
import com.helf.dto.SignupRequest;
import com.helf.service.AuthService;
import com.helf.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
  @Autowired
   private JwtUtils jwtUtils;
   @Autowired
   AuthenticationManager authenticationManager;
   @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws JsonProcessingException {
       return authService.registerUser(signUpRequest);
   }
    */
/*@PostMapping("/signin")
   public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignupRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

       SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
       List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
              userDetails.getEmail(),
               roles));
    }*//*


}




*/
