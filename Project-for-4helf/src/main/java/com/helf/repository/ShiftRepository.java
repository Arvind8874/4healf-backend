package com.helf.repository;

import com.helf.dto.ShiftRequest;
import com.helf.entity.Patients;
import com.helf.entity.Shift;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift,Long> {
    @Query("select s from Shift s where s.id =?1 or s.weekOff = ?2 or s.maximumPatient = ?3 or s.averageConsultationTime=?4"  )
    List<Shift> getShiftDetails(@Nullable Long id, @Nullable String weekOff, @Nullable String maximumPatient,@Nullable String averageConsultationTime);
    @Query("select s from Shift s order by s.createdTime DESC")
    List<Shift> searchAllShift(PageRequest pageableRequest);
}
