package com.helf.repository;

import com.helf.entity.Doctors;
import com.helf.entity.Patients;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patients,Long> {
    Optional<Patients> findByMobileNumber(String mobileNumber);

    @Query("select p from Patients p where p.id =?1 or p.mobileNumber = ?2")
    List<Patients> getPatientDetails(@Nullable Long id, @Nullable String mobileNumber);

    @Query("SELECT p from Patients p where p.createdTime >= :startDate AND p.createdTime < :endDate ORDER BY p.modifiedTime DESC")
    List<Patients> findByCreatedTime(@Param("startDate") Long startDate, @Param("endDate") Long endDate, Pageable pageable);

    @Query("SELECT p from Patients p where p.modifiedTime >= :startDate AND p.modifiedTime < :endDate ORDER BY p.modifiedTime DESC")
    List<Patients> findByGivenTwoDates(@Param("startDate") Long startDate, @Param("endDate") Long endDate, Pageable pageable);
    @Query("select p from Patients p order by p.createdTime DESC")
    List<Patients> searchAllPatient(PageRequest pageableRequest);
    //Doctors update(String product, String siteId)

}
