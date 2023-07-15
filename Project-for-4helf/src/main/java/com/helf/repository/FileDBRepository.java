package com.helf.repository;

import com.helf.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FileDBRepository extends JpaRepository<Documents,Long> {
}
