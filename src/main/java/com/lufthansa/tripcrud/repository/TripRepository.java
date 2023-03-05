package com.lufthansa.tripcrud.repository;

import com.lufthansa.tripcrud.entity.Trip;
import com.lufthansa.tripcrud.entity.TripStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository <Trip, Long>{
     List<Trip> findByStatus (TripStatusEnum status);

     Optional<Trip> findById(Long id);
     Trip findTripById(Long id);
     void deleteById(Long id);
}
