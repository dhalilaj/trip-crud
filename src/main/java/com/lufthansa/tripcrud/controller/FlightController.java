package com.lufthansa.tripcrud.controller;

import com.lufthansa.tripcrud.dto.FlightDto;
import com.lufthansa.tripcrud.dto.ResponseMsg;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightDto flightDto) {

        this.flightService.createFlight(flightDto);
        return ResponseEntity.ok(new ResponseMsg("You just created your flight"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        flightService.deleteById(id); //add service step
        return ResponseEntity.ok(new ResponseMsg("Flight deleted"));
    }

    @GetMapping
    public List<FlightDto> findAllFlights() {
        return flightService.findAll();
    }


}
