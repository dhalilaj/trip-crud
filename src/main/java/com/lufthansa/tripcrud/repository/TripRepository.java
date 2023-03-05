package com.lufthansa.tripcrud.repository;

import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository <Trip, Long>{
     List<Trip> findTripByStatus (String status);
     Trip findTripById(Long id);
     void deleteById(Long id);
}
