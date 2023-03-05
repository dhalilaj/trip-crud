package com.lufthansa.tripcrud.repository;

import com.lufthansa.tripcrud.entity.TripReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripReasonRepository extends JpaRepository<TripReason, Long> {
}
