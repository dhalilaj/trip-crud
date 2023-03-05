package com.lufthansa.tripcrud.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.lufthansa.tripcrud.dto.DeleteTripDto;
import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.dto.ResponseMsg;
import com.lufthansa.tripcrud.entity.Trip;
import com.lufthansa.tripcrud.entity.TripReasonEnum;
import com.lufthansa.tripcrud.repository.TripRepository;
import com.lufthansa.tripcrud.services.TripReasonService;
import com.lufthansa.tripcrud.services.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {
@Autowired
private DeleteTripDto deleteTripDto;
    public TripService tripService;

    public TripRepository tripRepository;

    public TripReasonService tripReasonService;

    @Autowired
    public TripController(TripService tripService, TripRepository tripRepository, TripReasonService tripReasonService) {
        this.tripService = tripService;
        this.tripRepository = tripRepository;
        this.tripReasonService = tripReasonService;
    }

    @GetMapping("/{status}")
    public List<TripDto> findTripByStatus(@PathVariable String status){
        return tripService.findTripByStatus(status);
    }

    @GetMapping
    public List<TripDto> findAllTrips(){
        return tripService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteTrip (@PathVariable DeleteTripDto deleteTripDto){
        this.tripService.deleteTrip(deleteTripDto.getTripId());
        return ResponseEntity.ok(new ResponseMsg("Trip deleted"));
    }

    @PostMapping
    public ResponseEntity<?> createTrip(@Valid @RequestBody TripDto tripDto){

    this.tripService.createTrip(tripDto.getFlight_id(), tripDto.getDescription(), tripDto.getOrigin(), tripDto.getDestination(),
            tripDto.getStatus(), tripDto.getDeparture_date(),
            tripDto.getArrival_date(), tripDto.getTripreason());

        return ResponseEntity.ok(new ResponseMsg("You just created your trip"));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateApplication(@Valid @RequestBody TripDto tripDto){
        this.tripService.updateTripStatus(tripDto.getTrip_id(), tripDto.getStatus());
        return ResponseEntity.ok(new ResponseMsg("Trip status updated successfully!"));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrip(@Valid@RequestBody TripDto tripDto){
    this.tripService.updateTrip(tripDto.getTrip_id(), tripDto.getDescription(), tripDto.getOrigin(), tripDto.getDestination(),
            tripDto.getDeparture_date(), tripDto.getArrival_date(), tripDto.getTripreason());
        return ResponseEntity.ok(new ResponseMsg("Trip updated successfully!"));
    }

}
