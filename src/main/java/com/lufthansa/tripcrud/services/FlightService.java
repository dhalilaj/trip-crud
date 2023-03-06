package com.lufthansa.tripcrud.services;

import com.lufthansa.tripcrud.dto.FlightDto;
import com.lufthansa.tripcrud.entity.Flight;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    void createFlight(FlightDto flightDto);
    List<FlightDto> findAll();
}
