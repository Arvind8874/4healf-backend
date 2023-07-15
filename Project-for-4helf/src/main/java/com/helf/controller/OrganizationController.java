package com.helf.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.helf.dto.OrganizationCriteria;
import com.helf.dto.OrganizationRequest;
import com.helf.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @PostMapping("/create")
    public ResponseEntity createOrganization(@Valid @RequestBody OrganizationRequest oraganizationRequest) throws JsonProcessingException {
        return organizationService.createOrganization(oraganizationRequest);
    }
    @GetMapping("/search")
    public ResponseEntity searchOrganization(@Valid @RequestBody OrganizationCriteria criteria) throws JsonProcessingException {
        return organizationService.searchOrganization(criteria);
    }
    @PutMapping("/Organization/{id}")
   public ResponseEntity updateOrganization(@Valid @PathVariable Long id , @RequestBody OrganizationRequest organizationRequest) throws JsonProcessingException{
        return organizationService.updateOrganization(id,organizationRequest);
   }
}
