package com.lufthansa.tripcrud.services.impl;

import com.lufthansa.tripcrud.converter.FlightConverter;
import com.lufthansa.tripcrud.dto.FlightDto;
import com.lufthansa.tripcrud.entity.Flight;
import com.lufthansa.tripcrud.repository.FlightRepository;
import com.lufthansa.tripcrud.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
    public void createFlight( int flight_nr, String origin, String destination, LocalDate departure_date, LocalDate arrival_date){
    Flight flight = new Flight(flight_nr, origin, destination, departure_date, arrival_date);
    this.flightRepository.save(flight);
    }

    @Override
    public List<FlightDto> findAll() {
        // use the converter FlightConverter -> convertToFlightDto(flight)
        return flightRepository.findAll().stream()
                .map(flight -> flightConverter.convertToDto(flight))
                .collect(Collectors.toList());
    }

//    @Override
//    public Flight findFlightByFlightNr(@PathVariable int flight_nr) {
//        return flightRepository.findFlightByFlightNr(flight_nr);
////                .stream()
////                .map(flight -> flightConverter.convertToDto(flight))
////                .collect(Collectors.toList());
//    }

//    @Override
//    public void deleteByFlightNr(@PathVariable int flight_nr){
//        Flight flight = flightRepository.findFlightByFlightNr(flight_nr);
//        flightRepository.delete(flight);
//    }


    @Override
    public void save(FlightDto flightDto) {

    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }


}
