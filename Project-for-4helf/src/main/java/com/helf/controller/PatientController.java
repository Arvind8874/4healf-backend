package com.helf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.helf.dto.PatientRequest;
import com.helf.dto.PatientSearchCriteria;
import com.helf.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patentService;

    @PostMapping("/signup")
    public ResponseEntity registerPatient(@Valid @RequestBody PatientRequest patientRequest) throws JsonProcessingException {
        return patentService.registerPatient(patientRequest);
    }

    @GetMapping("/search")
    public ResponseEntity searchPatient(@Valid @RequestBody PatientSearchCriteria criteria) throws JsonProcessingException {
        return patentService.searchPatient(criteria);
    }
    @PutMapping("/patients/{pId}")
    public ResponseEntity updatePatient(@Valid @PathVariable  Long pId,@RequestBody PatientRequest patientRequest) throws JsonProcessingException{
        return patentService.updatePatient(pId,patientRequest);
    }

}
