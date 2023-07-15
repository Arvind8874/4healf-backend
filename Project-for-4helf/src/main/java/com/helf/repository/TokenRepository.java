package com.helf.repository;


import com.helf.entity.Token;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    @Query("select t from Token t WHERE t.mobileNumber = :mobileNumber AND t.createdTime >= :startDate AND t.createdTime < :endDate order by t.createdTime desc")
    Optional<Token> findByPatientMobileNumber(@Param("mobileNumber") String mobileNumber, @Param("startDate") Long startDate, @Param("endDate") Long endDate);



}
