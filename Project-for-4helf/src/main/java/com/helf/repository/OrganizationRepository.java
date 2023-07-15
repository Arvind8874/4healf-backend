package com.helf.repository;

import com.helf.entity.Organization;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    @Query("select o from Organization o where o.id =?1 or o.hospitalName = ?2 or o.medicalCollege = ?3")
    List<Organization> getOrganizationDetails(@Nullable Long id, @Nullable String hospitalName, @Nullable String medicalCollege);
    @Query("select o from Organization o order by o.createdTime")
    List<Organization> searchAllOrganization(Pageable pageable);

}
