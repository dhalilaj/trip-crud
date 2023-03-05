package com.lufthansa.tripcrud.services.impl;

import com.lufthansa.tripcrud.converter.FlightConverter;
import com.lufthansa.tripcrud.dto.FlightDto;
import com.lufthansa.tripcrud.entity.Flight;
import com.lufthansa.tripcrud.repository.FlightRepository;
import com.lufthansa.tripcrud.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightConverter flightConverter;


    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, FlightConverter flightConverter) {
        this.flightRepository = flightRepository;
        this.flightConverter = flightConverter;
    }

    @Override
    public void createFlight( FlightDto flightDto){
    Flight flight = new Flight(flightDto.getFlight_nr(), flightDto.getOrigin(),flightDto.getDestination(),
            flightDto.getDeparture_date(),flightDto.getArrival_date());
    flightRepository.save(flight);
    }

    @Override
    public List<FlightDto> findAll() {
        return flightRepository.findAll().stream()
                .map(flight -> flightConverter.convertToDto(flight))
                .collect(Collectors.toList());
    }


//    @Override
//    public void updateFlight(FlightDto flightDto) {
//
//        Optional<Flight> flight = flightRepository.findById(flightDto.getId());
//
//
//        if (flight.isPresent()) {
//            flight.get().setFlight_nr(flightDto.getFlight_nr());
//            flight.get().setOrigin(flightDto.getOrigin());
//            flight.get().setDestination(flightDto.getDestination());
//            flight.get().setArrival_date(flightDto.getArrival_date());
//            flight.get().setDeparture_date(flightDto.getDeparture_date());
//            flightRepository.save(flight.get());
//        } else {
//            throw new RuntimeException("Flight does not exist!");
//        }
//
//    }









}
