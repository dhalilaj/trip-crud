package com.lufthansa.tripcrud.repository;

import com.lufthansa.tripcrud.dto.FlightDto;
import com.lufthansa.tripcrud.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
//    Flight findFlightByFlightNr(int flight_nr);

   // void deleteByFlightNr(int flight_nr) ;

}
