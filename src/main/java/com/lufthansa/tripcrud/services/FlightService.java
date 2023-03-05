package com.lufthansa.tripcrud.services;

import com.lufthansa.tripcrud.dto.FlightDto;
import com.lufthansa.tripcrud.entity.Flight;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    void createFlight(int flight_nr, String from, String to, LocalDate departure_date, LocalDate arrival_date);

    List<FlightDto> findAll();

//    List<FlightDto> findFlightByFlightNr(int flight_nr);

//    public void deleteByFlightNr(@PathVariable int flight_nr);

    void save (FlightDto flightDto);

    void delete(Long id);
}
