package com.aviation.core.repository;

import org.springframework.stereotype.Repository;
import com.aviation.core.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity,Long> {
}
