package com.helf.controller;


import com.helf.dto.TokenRequest;
import com.helf.dto.TokenSearchCriteria;
import com.helf.service.TokenService;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;
    @PostMapping("/create")
    public ResponseEntity createToken(@Valid @RequestBody TokenRequest tokenRequest){
        return tokenService.createToken(tokenRequest);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateToken(@Valid @PathVariable Long id,@RequestParam boolean isActive){
        return tokenService.updateToken(id,isActive);
    }

    @GetMapping("/search")
    public ResponseEntity searchToken(@Valid @RequestBody TokenSearchCriteria criteria){
        return tokenService.searchToken(criteria);
    }

}
