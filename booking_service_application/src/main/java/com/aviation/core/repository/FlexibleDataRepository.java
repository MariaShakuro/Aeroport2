package com.aviation.core.repository;

import com.aviation.core.entity.FlexibleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlexibleDataRepository extends JpaRepository<FlexibleData, Long> {
    FlexibleData findByPhoneNumber(String phoneNumber);
}
