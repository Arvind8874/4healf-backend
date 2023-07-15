package com.helf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.helf.dto.ShiftCriteria;
import com.helf.dto.ShiftRequest;
import com.helf.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/shift")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;
    @PostMapping("/createshift")
    public ResponseEntity createShift(@Valid @RequestBody ShiftRequest shiftRequest) throws JsonProcessingException {
        return shiftService.createShift(shiftRequest);
    }
    @GetMapping("/search")
    public ResponseEntity searchShift(@Valid @RequestBody ShiftCriteria shiftCriteria) throws JsonProcessingException{
        return shiftService.searchShift(shiftCriteria);

    }
    @PutMapping("/update/{sId}")
   public ResponseEntity updateShift(@Valid @PathVariable Long sId,@RequestBody ShiftRequest shiftRequest){
        return shiftService.updateShift(sId,shiftRequest);

   }
}
