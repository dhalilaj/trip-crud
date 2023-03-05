package com.lufthansa.tripcrud.services.impl;

import com.lufthansa.tripcrud.converter.TripConverter;
import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.entity.*;
import com.lufthansa.tripcrud.repository.FlightRepository;
import com.lufthansa.tripcrud.repository.TripRepository;
import com.lufthansa.tripcrud.repository.UserRepository;
import com.lufthansa.tripcrud.security.jwt.JwtProvider;
import com.lufthansa.tripcrud.services.TripService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TripConverter tripConverter;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private JwtProvider jwtUtils;

    @Autowired
    private HttpServletRequest request;


    @Override
    public List<TripDto> findAll() {
        // use the converter TripConverter -> convertToTripDto(trip)
        return tripRepository.findAll().stream()
                .map(trip -> tripConverter.convertToDto(trip))
                .collect(Collectors.toList());
    }

    @Override
    public List<TripDto> findTripByStatus(@PathVariable String status) {
        return tripRepository.findTripByStatus(status).stream()
                .map(trip -> tripConverter.convertToDto(trip))
                .collect(Collectors.toList());
    }

    @Override
    public void save(TripDto tripDto) {
    }

    @Override
    public Trip createTrip(Long flightId, String description, String from, String to, String status, LocalDate departure_date,
                           LocalDate arrival_date, TripReasonEnum trip_reason) {

        Flight flight = this.flightRepository.getReferenceById(flightId);

        String headerAuth = request.getHeader("Authorization");
        String jwt= headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User user = this.userRepository.findByUsername(username);

        Trip trip = new Trip(flight, user, description, from, to, status, departure_date, arrival_date, trip_reason);
        this.tripRepository.save(trip);

        flight.getTrip().add(trip);
        user.getTrip().add(trip);

        return trip;
    }

    @Override
    public void deleteTrip(Long tripId) {

        String headerAuth = request.getHeader("Authorization");
        String jwt= headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        User user = this.userRepository.findByUsername(username);
        Trip app= this.tripRepository.getReferenceById(tripId);
        Flight vehicle=app.getFlight();

        vehicle.getTrip().remove(app);
        user.getTrip().remove(app);
        this.tripRepository.deleteById(tripId);
    }




    @Override
    public void updateTrip(Long id, String newdescription, String neworigin, String newdestination,
                           LocalDate newdeparture_date, LocalDate newarrival_date, TripReasonEnum newtripreason) {

        String headerAuth = request.getHeader("Authorization");
        String jwt= headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        User user = this.userRepository.findByUsername(username);
        Trip trip= this.tripRepository.getReferenceById(id);
        Flight vehicle=trip.getFlight();

        //Trip trip = this.tripRepository.findTripById(id);
        trip.setArrival_date(newarrival_date);
        trip.setDescription(newdescription);
        trip.setTrip_reason(newtripreason);
        trip.setDeparture_date(newdeparture_date);
        trip.setOrigin(neworigin);
        trip.setDestination(newdestination);

        vehicle.getTrip().add(trip);
        user.getTrip().add(trip);

        this.flightRepository.save(vehicle);
        this.userRepository.save(user);

        this.tripRepository.save(trip);

    }


    @Override
    public Trip updateTripStatus(Long id, String status){
        String headerAuth = request.getHeader("Authorization");
        String jwt= headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        User user = this.userRepository.findByUsername(username);
        Trip app= this.tripRepository.getReferenceById(id);
        Flight vehicle=app.getFlight();

        app.setStatus(status);

        vehicle.getTrip().add(app);
        user.getTrip().add(app);

        this.flightRepository.save(vehicle);
        this.userRepository.save(user);

        return this.tripRepository.save(app);
    }


}
