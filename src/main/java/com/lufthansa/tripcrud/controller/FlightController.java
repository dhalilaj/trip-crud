package com.lufthansa.tripcrud.controller;

import com.lufthansa.tripcrud.dto.FlightDto;
import com.lufthansa.tripcrud.dto.ResponseMsg;
import com.lufthansa.tripcrud.entity.Flight;
import com.lufthansa.tripcrud.repository.FlightRepository;
import com.lufthansa.tripcrud.services.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    public FlightRepository flightRepository;
    public FlightService flightService;

    @Autowired
    public FlightController(FlightRepository flightRepository, FlightService flightService) {
        this.flightRepository = flightRepository;
        this.flightService = flightService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')") //TO BE TESTED
        public ResponseEntity<?> createFlight(@Valid @RequestBody FlightDto flightDto){

        this.flightService.createFlight(flightDto.getFlight_nr(), flightDto.getOrigin(), flightDto.getDestination(),
                flightDto.getDeparture_date(),flightDto.getArrival_date());
        return ResponseEntity.ok(new ResponseMsg("You just created your flight"));
    }

    @GetMapping
        public List<FlightDto> findAllFlights(){
            return flightService.findAll();
    }

    @DeleteMapping("/flightNr/{flight_nr}")
    public ResponseEntity<?> deleteById (@PathVariable Long id){
        flightRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseMsg("Flight deleted successfully!"));
    }

}
