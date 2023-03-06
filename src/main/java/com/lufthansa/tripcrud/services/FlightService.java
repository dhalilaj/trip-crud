package com.lufthansa.tripcrud.services;

import com.lufthansa.tripcrud.dto.FlightDto;

import java.util.List;

public interface FlightService {

    void createFlight(FlightDto flightDto);
    List<FlightDto> findAll();
}
