package com.helf.service;

import com.helf.dto.DoctorRequest;
import com.helf.dto.Errors;
import com.helf.dto.TokenRequest;
import com.helf.dto.TokenSearchCriteria;
import com.helf.entity.Token;
import com.helf.entity.enums.Status;
import com.helf.entity.enums.TokenType;
import com.helf.repository.TokenRepository;
import com.helf.repository.TokenRepositoryImpl;
import com.helf.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static com.helf.dto.Constants.*;
@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TokenRepositoryImpl tokenRepositoryImpl;

    private static Map<LocalDate, Integer> tokenMap = new HashMap<>();
    private static int initialTokenNumber = 1;
    public ResponseEntity createToken(TokenRequest tokenRequest) {
        try {
            Long currentTime= new Date().getTime();
            long startOfDay=CommonUtils.getStartDate(currentTime);
            LocalDate date = Instant.ofEpochMilli(currentTime).atZone(ZoneId.systemDefault()).toLocalDate();
            long endOfDay= CommonUtils.getEndDay(date);
           Optional<Token> token=tokenRepository.findByPatientMobileNumber(tokenRequest.getMobileNumber(),startOfDay,endOfDay);
            if ((token.isPresent()) && (!(token.get().getStatus().equals(Status.INACTIVE)))){
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(TOKEN_ALREADY_EXIST).build();
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            Token newToken = new Token();
            LocalDate currentDate = LocalDate.now();
            int tokenNumber = getNextTokenNumber(currentDate);
            storeTokenNumber(currentDate, tokenNumber);
            newToken.setTokenNumber(tokenNumber);
            newToken.setCreatedBy(1l);
            newToken.setMobileNumber(tokenRequest.getMobileNumber());
            newToken.setDoctorName(tokenRequest.getDoctorName());
            newToken.setClinicName(tokenRequest.getClinicName());
            newToken.setExpire(false);
           newToken.setTokenType(tokenRequest.getTokenType());
            newToken.setStatus(Status.DRAFTED);
            newToken.setCreatedTime(new Date().getTime());
            Token returnToken= tokenRepository.save(newToken);
            return new ResponseEntity<>(returnToken,HttpStatus.OK);
        } catch (Exception e) {
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(TOKEN_CREATE_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity updateToken(Long tId,boolean isActive) {
        try {
               Optional<Token> token1=tokenRepository.findById(tId);
               if (!token1.isPresent()){
                   Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(TOKEN_EXPIRE).build();
                   return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

               }
               Token oldtoken = token1.get();
               if (isActive==true){
                   oldtoken.setStatus(Status.ACTIVE);
               }else {
                   oldtoken.setStatus(Status.INACTIVE);
                   oldtoken.setExpire(true);
               }
              oldtoken.setModifiedBy(1l);
              oldtoken.setModifiedTime(new Date().getTime());
              tokenRepository.save(oldtoken);
              TokenRequest request=modelMapper.map(oldtoken,TokenRequest.class);
              return new ResponseEntity<>(request,HttpStatus.OK);
              }
         catch (Exception e) {
             Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(TOKEN_UPDATE_FAILED).build();
             return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity searchToken(TokenSearchCriteria criteria) {
        try {
            List<Token> tokenList=null;
            tokenList=tokenRepositoryImpl.getTokenDetails(criteria);

            if (tokenList.isEmpty()){
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(TOKEN_EXPIRE).build();
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            List<TokenRequest> tokenRequests=modelMapper.map(tokenList, new TypeToken<List<TokenRequest>>(){}.getType());
            return new ResponseEntity<>(tokenRequests,HttpStatus.OK);

        } catch (Exception e) {
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(TOKEN_SEARCH_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public static int getNextTokenNumber(LocalDate currentDate) {
        if (tokenMap.containsKey(currentDate)) {
            return tokenMap.get(currentDate) + 1;
        } else {
            return initialTokenNumber;
        }
    }

    public static void storeTokenNumber(LocalDate currentDate, int tokenNumber) {
        tokenMap.put(currentDate, tokenNumber);
    }
}
