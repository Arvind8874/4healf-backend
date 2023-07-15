package com.helf.service;

import com.helf.dto.*;
import com.helf.entity.Doctors;
import com.helf.entity.Organization;
import com.helf.repository.DoctorRepository;
import com.helf.repository.OrganizationRepository;
import com.helf.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.helf.dto.Constants.*;
import static com.helf.dto.Constants.DOCTOR_UPDATE_FAILED;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DoctorRepository doctorRepository;
    public ResponseEntity createOrganization(OrganizationRequest oraganizationRequest){
    try {
        Optional<Doctors> doctors=doctorRepository.findById(oraganizationRequest.getDoctorsId());
        if (!doctors.isPresent()){
            Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(DOCTOR_NOT_FOUND).build();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Organization newOrganization=new Organization();
        newOrganization.setCity(oraganizationRequest.getCity());
        newOrganization.setFees(oraganizationRequest.getFees());
        newOrganization.setAwarded(oraganizationRequest.getAwarded());
       // newOrganization.setDuration(oraganizationRequest.getDuration());
        newOrganization.setPracticingSince(oraganizationRequest.getPracticingSince());
        newOrganization.setPracticingField(oraganizationRequest.getPracticingField());
        newOrganization.setHospitalName(oraganizationRequest.getHospitalName());
        newOrganization.setGstNo(oraganizationRequest.getGstNo());
       // newOrganization.setYear(oraganizationRequest.getYear());
        newOrganization.setMedicalCollege(oraganizationRequest.getMedicalCollege());
        newOrganization.setAwardedBy(oraganizationRequest.getAwardedBy());
        newOrganization.setState(oraganizationRequest.getState());
        newOrganization.setOfficeContactNo1(oraganizationRequest.getOfficeContactNo1());
        newOrganization.setOfficeContactNo2(oraganizationRequest.getOfficeContactNo2());
        newOrganization.setPinCode(oraganizationRequest.getPinCode());
        newOrganization.setDoctorId(oraganizationRequest.getDoctorsId());
        newOrganization.setYear(new Date().getTime());
        newOrganization.setDuration(new Date().getTime());
        newOrganization.setCreatedBy(1l);
        newOrganization.setCreatedTime(new Date().getTime());
        newOrganization.setModifiedBy(1l);
        newOrganization.setModifiedTime(new Date().getTime());
        organizationRepository.save(newOrganization);
        OrganizationRequest oraganizationRequest1=modelMapper.map(newOrganization, OrganizationRequest.class);
        return new ResponseEntity<>(oraganizationRequest1,HttpStatus.OK);

    }catch (Exception e){
        Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(ORGANIZATION_SIGNUP_FAILED).build();
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    public ResponseEntity searchOrganization(OrganizationCriteria organizationCriteria) {
        try {
            List<Organization> organizationList=null;
            int pageNumber = (organizationCriteria.getOffset() / organizationCriteria.getLimit()) ; // calculate page number
            int pageSize = organizationCriteria.getLimit(); // set page size to limit
            PageRequest pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "modifiedTime");
            if (organizationCriteria.getId()!=null || organizationCriteria.getHospitalName()!=null || organizationCriteria.getMedicalCollege()!=null){
                organizationList=organizationRepository.getOrganizationDetails(organizationCriteria.getId(), organizationCriteria.getHospitalName(),organizationCriteria.getMedicalCollege());
            }
            else {
                organizationList=organizationRepository.searchAllOrganization(pageableRequest);
            }
            if (organizationList.isEmpty()){
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(ORGANIZATION_NOT_FOUND).build();
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            List<OrganizationRequest> organizationRequests=modelMapper.map(organizationList, new TypeToken<List<OrganizationRequest>>(){}.getType());
            return new ResponseEntity<>(organizationRequests,HttpStatus.OK);
        }catch (Exception e){
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(ORGANIZATION_SEARCH_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity updateOrganization(Long dId,OrganizationRequest organizationRequest) {
        try {
            Optional<Organization> organization=organizationRepository.findById(dId);
            if (!organization.isPresent()){
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(ORGANIZATION_NOT_FOUND).build();
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            Organization oldOrganization= organization.get();
            if (organizationRequest.getCity()!=null){
                oldOrganization.setCity(organizationRequest.getCity());
            }
            if (organizationRequest.getHospitalName()!=null){
                oldOrganization.setHospitalName(organizationRequest.getHospitalName());
            }
            if(organizationRequest.getFees()!=null){
                oldOrganization.setFees(organizationRequest.getFees());
            }
            if (organizationRequest.getAwarded()!=null){
                oldOrganization.setAwarded(organizationRequest.getAwarded());
            }
            if (organizationRequest.getAwardedBy()!=null){
                oldOrganization.setAwardedBy(organizationRequest.getAwardedBy());
            }
            if (organizationRequest.getMedicalCollege()!=null){
                oldOrganization.setMedicalCollege(organizationRequest.getMedicalCollege());
            }
            if (organizationRequest.getOfficeContactNo1()!=null){
                oldOrganization.setOfficeContactNo1(organizationRequest.getOfficeContactNo1());
            }
            if (organizationRequest.getOfficeContactNo2()!=null){
                oldOrganization.setOfficeContactNo2(organizationRequest.getOfficeContactNo2());
            }
            if (organizationRequest.getPinCode()!=null){
                oldOrganization.setPinCode(organizationRequest.getPinCode());
            }
            if (organizationRequest.getState()!=null){
                oldOrganization.setState(organizationRequest.getState());
            }
            if (organizationRequest.getPracticingField()!=null){
                oldOrganization.setPracticingField(organizationRequest.getPracticingField());
            }
            if (organizationRequest.getPracticingSince()!=null){
                oldOrganization.setPracticingSince(organizationRequest.getPracticingSince());
            }
          /* if (organizationRequest.getYear()!=null){
               oldOrganization.setYear(organizationRequest.getYear());
           }*/
            if (organizationRequest.getGstNo()!=null){
               oldOrganization.setGstNo(organizationRequest.getGstNo());
            }
            oldOrganization.setYear(new Date().getTime());
            oldOrganization.setDuration(new Date().getTime());
            organizationRepository.save(oldOrganization);
            OrganizationRequest organizationRequest1=modelMapper.map(oldOrganization,OrganizationRequest.class);
            return new ResponseEntity<>(organizationRequest1,HttpStatus.OK);
        }catch (Exception e){
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(ORGANIZATION_UPDATE_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}


