package com.lufthansa.tripcrud.controller;


import com.lufthansa.tripcrud.dto.AttachFlightRequest;
import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.dto.ResponseMsg;
import com.lufthansa.tripcrud.entity.Trip;
import com.lufthansa.tripcrud.entity.TripStatusEnum;
import com.lufthansa.tripcrud.exception.AttachFlightException;
import com.lufthansa.tripcrud.exception.FlightNotFoundException;
import com.lufthansa.tripcrud.exception.TripNotFoundException;
import com.lufthansa.tripcrud.repository.FlightRepository;
import com.lufthansa.tripcrud.repository.TripRepository;
import com.lufthansa.tripcrud.services.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    public TripService tripService;

    public TripRepository tripRepository;

    public FlightRepository flightRepository;

    @Autowired
    public TripController(TripService tripService, TripRepository tripRepository, FlightRepository flightRepository) {
        this.tripService = tripService;
        this.tripRepository = tripRepository;
        this.flightRepository = flightRepository;
    }


    @GetMapping("/{status}")
    public List<TripDto> findTripByStatus(@PathVariable TripStatusEnum status) {
        return tripService.findTripByStatus(status);
    }

    @PostMapping
    public ResponseEntity<?> createTrip(@Valid @RequestBody TripDto tripDto) {
        tripService.createTrip(tripDto);
        return ResponseEntity.ok(new ResponseMsg("You just created your trip"));
    }

    @PutMapping
    public ResponseEntity<?> updateTrip(@Valid @RequestBody TripDto tripDto) throws TripNotFoundException {
        tripService.updateTrip(tripDto);
        return ResponseEntity.ok(new ResponseMsg("Trip updated successfully!"));
    }

    @GetMapping
    public List<TripDto> findAllTrips() {
        return tripService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) throws TripNotFoundException {
        if (!tripRepository.existsById(id)) {
            throw new TripNotFoundException(id);
        }
        tripService.deleteById(id);
        return ResponseEntity.ok(new ResponseMsg("Trip deleted"));
    }


    @PutMapping("/{id}/askApproval")
    public ResponseEntity<?> askApproval(@PathVariable Long id) throws TripNotFoundException {
        if (!tripRepository.existsById(id)) {
            throw new TripNotFoundException(id);
        }
        tripService.updateStatus(id, TripStatusEnum.WAITING_FOR_APPROVAL);
        return ResponseEntity.ok(new ResponseMsg("Trip status updated to WAITING_FOR_APPROVAL !"));
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> approve(@PathVariable Long id) throws TripNotFoundException {
        if (!tripRepository.existsById(id)) {
            throw new TripNotFoundException(id);
        }
        tripService.updateStatus(id, TripStatusEnum.APPROVED);
        return ResponseEntity.ok(new ResponseMsg("Trip status updated to APPROVED!"));
    }

    @PutMapping("/attachFlight")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> attachFlight(@Valid @RequestBody AttachFlightRequest attachFlightRequest) throws FlightNotFoundException, AttachFlightException, TripNotFoundException {
        if (!tripRepository.existsById(attachFlightRequest.getTrip_id())) {
            throw new TripNotFoundException(attachFlightRequest.getTrip_id());
        }
        if (!flightRepository.existsById(attachFlightRequest.getFlight_id())) {
            throw new FlightNotFoundException(attachFlightRequest.getFlight_id());
        }
        if (tripRepository.existsById(attachFlightRequest.getTrip_id())) {
            Trip trip = tripRepository.findById(attachFlightRequest.getTrip_id()).get();
            if (trip.getStatus() != TripStatusEnum.APPROVED) {
                throw new AttachFlightException("Cannot add flight to a non Approved trip");
            }
        }
        tripService.attachFlight(attachFlightRequest);
        return ResponseEntity.ok(new ResponseMsg("Flight is added to your trip!"));
    }

}
