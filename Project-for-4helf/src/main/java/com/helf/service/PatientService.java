package com.helf.service;

import com.helf.dto.Errors;
import com.helf.dto.PatientRequest;
import com.helf.dto.PatientSearchCriteria;
import com.helf.entity.Patients;
import com.helf.repository.PatientRepository;
import com.helf.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.helf.dto.Constants.*;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;
    public ResponseEntity registerPatient(PatientRequest patientRequest) {
        try {
            Optional<Patients> patients=patientRepository.findByMobileNumber(patientRequest.getMobileNumber());
            if (patients.isPresent()){
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(PATIENT_ALREADY_EXIST).build();
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            Patients newPatient=new Patients();
            newPatient.setFirstName(patientRequest.getFirstName());
            newPatient.setLastName(patientRequest.getLastName());
            newPatient.setAge(patientRequest.getAge());
            newPatient.setAddress(patientRequest.getAddress());
            newPatient.setPassword(patientRequest.getPassword());
            newPatient.setGender(patientRequest.getGender());
            newPatient.setEmail(patientRequest.getEmail());
            newPatient.setMobileNumber(patientRequest.getMobileNumber());
            newPatient.setCreatedBy(null);
            newPatient.setCreatedTime(new Date().getTime());
            newPatient.setModifiedBy(null);
            newPatient.setModifiedTime(new Date().getTime());
            patientRepository.save(newPatient);
            PatientRequest patientRequest1=modelMapper.map(newPatient,PatientRequest.class);
           return new ResponseEntity<>(patientRequest1,HttpStatus.OK);
        }catch (Exception e){
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(PATIENT_SIGNUP_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity searchPatient(PatientSearchCriteria criteria) {
        try {
            List<Patients> patientsList=null;
            int pageNumber = (criteria.getOffset() / criteria.getLimit()) ; // calculate page number
            int pageSize = criteria.getLimit(); // set page size to limit
            PageRequest pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "modifiedTime");
            if (criteria.getId()!=null || criteria.getMobileNumber()!=null){
                patientsList=patientRepository.getPatientDetails(criteria.getId(), criteria.getMobileNumber());
            }
            else if (criteria.getCreatedTime()!=null|| criteria.getModifiedTime()!=null){
                patientsList=searchUtil(criteria.getCreatedTime(),criteria.getModifiedTime(),pageableRequest);
            }
            else {
                patientsList=patientRepository.searchAllPatient(pageableRequest);
            }
            if (patientsList.isEmpty()){
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(PATIENT_NOT_FOUND).build();
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            List<PatientRequest> patientRequests=modelMapper.map(patientsList, new TypeToken<List<PatientRequest>>(){}.getType());
            return new ResponseEntity<>(patientRequests,HttpStatus.OK);
        }catch (Exception e){
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(PATIENT_SEARCH_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Patients> searchUtil (Long fromTime, Long toTime,  PageRequest pageableRequest){
        Long startTime = fromTime==null ? toTime : fromTime;
        Long endTime = toTime==null ? fromTime : toTime;
        long startOfDay=CommonUtils.getStartDate(startTime);
        LocalDate date = Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault()).toLocalDate();
        long endOfDay= CommonUtils.getEndDay(date);
        return fromTime!=null && toTime==null ? patientRepository.findByCreatedTime(startOfDay,endOfDay,pageableRequest) :patientRepository.findByGivenTwoDates(startOfDay,endOfDay, pageableRequest);
    }


    public ResponseEntity updatePatient(Long pId,PatientRequest patientRequest) {
        try {
            Optional<Patients> patients=patientRepository.findById(pId);
           // Optional<Patients> patients=patientRepository.findByMobileNumber(patientRequest.getMobileNumber());
            if (!patients.isPresent()){
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(PATIENT_NOT_FOUND).build();
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            Patients oldPatient=patients.get();
            if (patientRequest.getFirstName()!=null) {
                oldPatient.setFirstName(patientRequest.getFirstName());
            }
            if(patientRequest.getLastName()!=null){
                oldPatient.setLastName(patientRequest.getLastName());
            }
            if(patientRequest.getAge()!=null){
                oldPatient.setAge(patientRequest.getAge());
            }
            if (patientRequest.getAddress()!=null){
                oldPatient.setAddress(patientRequest.getAddress());
            }

          if (patientRequest.getEmail()!=null){
              oldPatient.setEmail(patientRequest.getEmail());
          }
            if (patientRequest.getMobileNumber()!=null){
                oldPatient.setMobileNumber(patientRequest.getMobileNumber());
            }
            oldPatient.setModifiedBy(null);
            oldPatient.setModifiedTime(new Date().getTime());
            patientRepository.save(oldPatient);
            PatientRequest patientRequest1=modelMapper.map(oldPatient,PatientRequest.class);
            return new ResponseEntity<>(patientRequest1,HttpStatus.OK);
        }catch (Exception e){
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(PATIENT_UPDATE_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
