package com.helf.repository;

import com.helf.dto.DoctorRequest;
import com.helf.entity.Doctors;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@EnableJpaRepositories

@Repository
public interface DoctorRepository extends JpaRepository<Doctors,Long> {
   @Query("select d from Doctors d where d.id =?1 or d.mobileNumber = ?2 or d.userName = ?3")
   List<Doctors> getDoctorDetails(@Nullable Long id, @Nullable String mobileNumber, @Nullable String userName);
//@Query("SELECT d, o FROM Doctors d JOIN d.organization o WHERE d.id = ?1 OR d.mobileNumber = ?2 OR d.userName = ?3")
//List<Doctors> getDoctorDetails(@Nullable Long id, @Nullable String mobileNumber, @Nullable String userName);


    @Query("SELECT d from Doctors d where d.createdTime >= :startDate AND d.createdTime < :endDate ORDER BY d.modifiedTime DESC")
    List<Doctors> findByCreatedTime(@Param("startDate") Long startDate, @Param("endDate") Long endDate, Pageable pageable);

    @Query("SELECT d from Doctors d where d.modifiedTime >= :startDate AND d.modifiedTime < :endDate ORDER BY d.modifiedTime DESC")
    List<Doctors> findByGivenTwoDates(@Param("startDate") Long startDate, @Param("endDate") Long endDate, Pageable pageable);
    @Query("select d from Doctors d order by d.createdTime DESC")
    List<Doctors> searchAllDoctors(PageRequest pageableRequest);


    Optional<Doctors> findByUserName(String userName);


    //void updateDoctor(Long dId,DoctorRequest doctorRequest);
}


