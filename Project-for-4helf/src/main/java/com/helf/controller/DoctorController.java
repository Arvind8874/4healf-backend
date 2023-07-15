package com.helf.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.helf.dto.DoctorRequest;
import com.helf.dto.DoctorSearchCriteria;
import com.helf.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/signup")
    public ResponseEntity registerDoctor(@Valid @RequestBody DoctorRequest doctorRequest) throws JsonProcessingException {
        return doctorService.registerDoctor(doctorRequest);
    }
    @GetMapping("/search")
    public ResponseEntity searchDoctor(@Valid @RequestBody DoctorSearchCriteria criteria) throws JsonProcessingException {
        return doctorService.searchDoctor(criteria);
    }
    @PutMapping("/doctor/{dId}")
    public ResponseEntity updateDoctor(@Valid @PathVariable Long dId ,@RequestBody DoctorRequest doctorRequest) throws JsonProcessingException{
        return doctorService.updateDoctor(dId,doctorRequest);
    }
}
