package com.helf.service;

import com.helf.dto.*;
import com.helf.entity.Doctors;

import com.helf.repository.DoctorRepository;
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
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapper modelMapper;
    public ResponseEntity registerDoctor(DoctorRequest doctorRequest) {
        try {
            Optional<Doctors> doctors=doctorRepository.findByUserName(doctorRequest.getUserName());
            if (doctors.isPresent()){
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(DOCTOR_ALREADY_EXIST).build();
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            Doctors newDoctors=new Doctors();
            newDoctors.setFirstName(doctorRequest.getFirstName());
            newDoctors.setLastName(doctorRequest.getLastName());
            newDoctors.setMiddleName(doctorRequest.getMiddleName());
            newDoctors.setDateOfRegistration(doctorRequest.getDateOfRegistration());
            newDoctors.setStateMedicalCouncil(doctorRequest.getStateMedicalCouncil());
            newDoctors.setUserName(doctorRequest.getUserName());
            newDoctors.setEmail(doctorRequest.getEmail());
            newDoctors.setPassword(doctorRequest.getPassword());
            newDoctors.setMobileNumber(doctorRequest.getMobileNumber());
            newDoctors.setDob(doctorRequest.getDob());
            newDoctors.setGender(doctorRequest.getGender());
            newDoctors.setDrRegistrationNo(doctorRequest.getDrRegistrationNo());
            newDoctors.setCreatedBy(1l);
            newDoctors.setCreatedTime(new Date().getTime());
            newDoctors.setModifiedBy(1l);
            newDoctors.setModifiedTime(new Date().getTime());
            doctorRepository.save(newDoctors);
            DoctorRequest doctorRequest1=modelMapper.map(newDoctors,DoctorRequest.class);
            return new ResponseEntity<>(doctorRequest1,HttpStatus.OK);

        }catch (Exception e){
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(DOCTOR_SIGNUP_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity searchDoctor(DoctorSearchCriteria criteria) {
        try {

            List<Doctors> doctorsList=null;
            int pageNumber = (criteria.getOffset() / criteria.getLimit()) ; // calculate page number
            int pageSize = criteria.getLimit(); // set page size to limit
            PageRequest pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "modifiedTime");
            if (criteria.getId()!=null || criteria.getUserName()!=null || criteria.getMobileNumber()!=null){
                doctorsList=doctorRepository.getDoctorDetails(criteria.getId(), criteria.getMobileNumber(), criteria.getUserName());

            }
            else if (criteria.getCreatedTime()!=null|| criteria.getModifiedTime()!=null){
                doctorsList=searchUtil(criteria.getCreatedTime(),criteria.getModifiedTime(),pageableRequest);
            }
            else {
                doctorsList=doctorRepository.searchAllDoctors(pageableRequest);
            }
            if (doctorsList.isEmpty()){
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(DOCTOR_NOT_FOUND).build();
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            List<DoctorRequest> doctorRequests=modelMapper.map(doctorsList, new TypeToken<List<DoctorRequest>>(){}.getType());
            return new ResponseEntity<>(doctorRequests,HttpStatus.OK);
        }catch (Exception e){
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(DOCTOR_SEARCH_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Doctors> searchUtil (Long fromTime, Long toTime,  PageRequest pageableRequest){
        Long startTime = fromTime==null ? toTime : fromTime;
        Long endTime = toTime==null ? fromTime : toTime;
        long startOfDay= CommonUtils.getStartDate(startTime);
        LocalDate date = Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault()).toLocalDate();
        long endOfDay= CommonUtils.getEndDay(date);
        return fromTime!=null && toTime==null ? doctorRepository.findByCreatedTime(startOfDay,endOfDay,pageableRequest) :doctorRepository.findByGivenTwoDates(startOfDay,endOfDay, pageableRequest);
    }
    public ResponseEntity updateDoctor(Long dId,DoctorRequest doctorRequest) {
        try {
            Optional<Doctors> doctors=doctorRepository.findById(dId);
            if (!doctors.isPresent()){
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(DOCTOR_NOT_FOUND).build();
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            Doctors oldDoctor= doctors.get();
            if (doctorRequest.getUserName()!=null){
                oldDoctor.setUserName(doctorRequest.getUserName());
            }
            if (doctorRequest.getFirstName()!=null){
                oldDoctor.setFirstName(doctorRequest.getFirstName());
            }
            if (doctorRequest.getMiddleName()!=null){
                oldDoctor.setMiddleName(doctorRequest.getMiddleName());
            }

            if (doctorRequest.getDateOfRegistration()!=null){
                oldDoctor.setDateOfRegistration(doctorRequest.getDateOfRegistration());
            }
            if(doctorRequest.getDob()!=null){
                oldDoctor.setDob(doctorRequest.getDob());
            }
            if (doctorRequest.getDrRegistrationNo()!=null){
                oldDoctor.setDrRegistrationNo(doctorRequest.getDrRegistrationNo());
            }
            if (doctorRequest.getStateMedicalCouncil()!=null){
                oldDoctor.setStateMedicalCouncil(doctorRequest.getStateMedicalCouncil());
            }

            if (doctorRequest.getLastName()!=null){
                oldDoctor.setLastName(doctorRequest.getLastName());
            }
            if (doctorRequest.getMobileNumber()!=null){
                oldDoctor.setMobileNumber(doctorRequest.getMobileNumber());
            }
            if (doctorRequest.getEmail()!=null){
                oldDoctor.setEmail(doctorRequest.getEmail());
            }

            oldDoctor.setModifiedBy(1l);
            oldDoctor.setModifiedTime(new Date().getTime());
            doctorRepository.save(oldDoctor);
            DoctorRequest doctorRequest1=modelMapper.map(oldDoctor,DoctorRequest.class);
            return new ResponseEntity<>(doctorRequest1,HttpStatus.OK);
        }catch (Exception e){
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(DOCTOR_UPDATE_FAILED).build();
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public DoctorRequest getUserByUsername(String userName) {
        Optional<Doctors> returnDoctor = doctorRepository.findByUserName(userName);
        Doctors user=returnDoctor.get();
        if (user!=null) {
            DoctorRequest userDTO = modelMapper.map(user, DoctorRequest.class);
//            userDTO.setActions(getActions(user));
            return userDTO;
        }else
            throw new OAuth2Exception("Invalid login credentials");
    }
}
