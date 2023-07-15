package com.helf.service;
import com.helf.dto.Errors;

import com.helf.dto.ShiftCriteria;
import com.helf.dto.ShiftRequest;
import com.helf.entity.Shift;
import com.helf.repository.ShiftRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.helf.dto.Constants.*;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
     private ModelMapper modelMapper;
    public ResponseEntity createShift(ShiftRequest shiftRequest) {
        try {
            Shift newTotalShift = new Shift();
            newTotalShift.setBreakDuration(shiftRequest.getBreakDuration());
            newTotalShift.setMaximumPatient(shiftRequest.getMaximumPatient());
            newTotalShift.setAverageConsultationTime(shiftRequest.getAverageConsultationTime());
            newTotalShift.setWeekOff(shiftRequest.getWeekOff());
            newTotalShift.setStartDate(shiftRequest.getStartDate());
            newTotalShift.setEndDate(shiftRequest.getEndDate());
            newTotalShift.setFromDate(new Date().getTime());
            newTotalShift.setToDate(new Date().getTime());
            newTotalShift.setModifiedTime(new Date().getTime());
            newTotalShift.setCreatedTime(new Date().getTime());
             shiftRepository.save(newTotalShift);
             ShiftRequest shiftRequest1=modelMapper.map(newTotalShift,ShiftRequest.class);
             return new ResponseEntity(shiftRequest1, HttpStatus.OK);

        }catch (Exception e){
            Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(SHIFT_FAILED).build();
                   return new ResponseEntity(errors,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity searchShift(ShiftCriteria shiftCriteria) {
        try {
            List<Shift> shiftList = null;
            int pageNumber = (shiftCriteria.getOffset() / shiftCriteria.getLimit()); // calculate page number
            int pageSize = shiftCriteria.getLimit(); // set page size to limit
            PageRequest pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "modifiedTime");
            if (shiftCriteria.getId() != null || shiftCriteria.getMaximumPatient() != null || shiftCriteria.getWeekOff() != null) {
                shiftList = shiftRepository.getShiftDetails(shiftCriteria.getId(), shiftCriteria.getWeekOff(), shiftCriteria.getMaximumPatient(), shiftCriteria.getAverageConsultationTime());
            } else {
                shiftList = shiftRepository.searchAllShift(pageableRequest);
            }
                if (shiftList.isEmpty()) {
                    Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(SHIFT_NOT_FOUND).build();
                    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
                }
                List<ShiftRequest> shiftRequests = modelMapper.map(shiftList, new TypeToken<List<ShiftRequest>>() {
                }.getType());
                return new ResponseEntity<>(shiftRequests, HttpStatus.OK);
            }
            catch(Exception e){
                Errors errors = Errors.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).errorMessage(PATIENT_SEARCH_FAILED).build();
                return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    public ResponseEntity updateShift(Long sId, ShiftRequest shiftRequest) {
        try {
            Optional<Shift> shift = shiftRepository.findById(sId);
            if (!shift.isPresent()) {
                Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(SHIFT_NOT_FOUND).build();
                return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
            }
            Shift shift1 = shift.get();
            if (shiftRequest.getMaximumPatient() != null) {
                shift1.setMaximumPatient(shiftRequest.getMaximumPatient());
            }
            if (shiftRequest.getWeekOff() != null) {
                shift1.setWeekOff(shiftRequest.getWeekOff());

            }
            if (shiftRequest.getAverageConsultationTime() != null) {
                shift1.setAverageConsultationTime(shiftRequest.getAverageConsultationTime());
            }
            if (shiftRequest.getStartDate()!=null){
                shift1.setStartDate(shiftRequest.getStartDate());
            }
            if (shiftRequest.getEndDate()!=null){
                shift1.setEndDate(shiftRequest.getEndDate());
            }
            shift1.setModifiedBy(null);
            shift1.setModifiedTime(new Date().getTime() );
            shiftRepository.save(shift1);
            ShiftRequest shiftRequest1=modelMapper.map(shift1,ShiftRequest.class);
            return new ResponseEntity(shiftRequest1,HttpStatus.OK);

        } catch (Exception e) {
            Errors errors = Errors.builder().errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorMessage(SHIFT_UPDATE_FAILED).build();
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);

        }

    }
}



