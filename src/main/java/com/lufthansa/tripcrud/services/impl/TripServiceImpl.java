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

import java.util.List;
import java.util.Optional;
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
    public List<TripDto> findTripByStatus(@PathVariable TripStatusEnum status) {
        return tripRepository.findByStatus(status).stream()
                .map(trip -> tripConverter.convertToDto(trip))
                .collect(Collectors.toList());
    }

    @Override
    public void save(TripDto tripDto) {
    }

    @Override
    public Trip createTrip(TripDto tripDto) {

//        Flight flight = flightRepository.getReferenceById(flightId);

        String headerAuth = request.getHeader("Authorization");
        String jwt = headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User user = userRepository.findByUsername(username);

        Trip trip = new Trip(user, tripDto.getDescription(), tripDto.getOrigin(), tripDto.getDestination(),
                TripStatusEnum.CREATED, tripDto.getDeparture_date(), tripDto.getArrival_date(), tripDto.getTripreason());

        tripRepository.save(trip);

//        flight.getTrip().add(trip);
        user.getTrip().add(trip);

        return trip;
    }

    @Override
    public void deleteTrip(Long tripId) {

        String headerAuth = request.getHeader("Authorization");
        String jwt = headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        User user = userRepository.findByUsername(username);
        Trip trip = tripRepository.getReferenceById(tripId);
        Flight vehicle = trip.getFlight();

        vehicle.getTrip().remove(trip);
        user.getTrip().remove(trip);
        tripRepository.deleteById(tripId);
    }


    @Override
    public void updateTrip(TripDto tripDto) {

        Trip trip = tripRepository.getReferenceById(tripDto.getId());

        trip.setDescription(tripDto.getDescription());
        trip.setOrigin(tripDto.getOrigin());
        trip.setDestination(tripDto.getDestination());
        trip.setDeparture_date(tripDto.getDeparture_date());
        trip.setArrival_date(tripDto.getArrival_date());
        trip.setReason(tripDto.getTripreason());

        tripRepository.save(trip);

    }


    @Override
    public void updateStatus(Long id, TripStatusEnum status) {

        Optional<Trip> trip = tripRepository.findById(id);

        if (trip.isPresent()) {
            trip.get().setStatus(status);
        } else {
            throw new RuntimeException("Trip does not exist!");
        }
    }




}
