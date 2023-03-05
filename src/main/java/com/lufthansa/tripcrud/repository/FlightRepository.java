package com.lufthansa.tripcrud.repository;

import com.lufthansa.tripcrud.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
