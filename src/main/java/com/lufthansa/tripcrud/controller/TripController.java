package com.lufthansa.tripcrud.controller;

import com.lufthansa.tripcrud.dto.DeleteTripDto;
import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.dto.ResponseMsg;
import com.lufthansa.tripcrud.entity.TripStatusEnum;
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
    @Autowired
    private DeleteTripDto deleteTripDto;
    public TripService tripService;

    public TripRepository tripRepository;


    @Autowired
    public TripController(TripService tripService, TripRepository tripRepository) {
        this.tripService = tripService;
        this.tripRepository = tripRepository;
    }

    @GetMapping("/{status}")
    public List<TripDto> findTripByStatus(@PathVariable TripStatusEnum status) {
        return tripService.findTripByStatus(status);
    }

//    @GetMapping
//    public List<TripDto> findAllTrips(){
//        return tripService.findAll();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        tripRepository.deleteById(id); //add service step
        return ResponseEntity.ok(new ResponseMsg("Trip deleted"));
    }

    @PostMapping
    public ResponseEntity<?> createTrip(@Valid @RequestBody TripDto tripDto) {
        tripService.createTrip(tripDto);
        return ResponseEntity.ok(new ResponseMsg("You just created your trip"));
    }

    @PutMapping
    public ResponseEntity<?> updateTrip(@Valid @RequestBody TripDto tripDto) {
        tripService.updateTrip(tripDto);
        return ResponseEntity.ok(new ResponseMsg("Trip updated successfully!"));
    }


    @PutMapping("/{id}/askApproval")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> askApproval(@PathVariable Long id) {
        tripService.updateStatus(id, TripStatusEnum.WAITING_FOR_APPROVAL);
        return ResponseEntity.ok(new ResponseMsg("Trip status updated to WAITING_FOR_APPROVAL !"));
    }

    @PutMapping("/{id}/approve")
//    @PreAuthorize("hasRole('ADMIN')") //to be tested
    public ResponseEntity<?> approve(@PathVariable Long id) {
        tripService.updateStatus(id, TripStatusEnum.APPROVED); //to be tested
        return ResponseEntity.ok(new ResponseMsg("Trip status updated to APPROVED!"));
    }

}
