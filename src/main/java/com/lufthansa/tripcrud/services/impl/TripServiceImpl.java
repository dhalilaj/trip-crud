package com.lufthansa.tripcrud.services.impl;

import com.lufthansa.tripcrud.converter.TripConverter;
import com.lufthansa.tripcrud.dto.AttachFlightRequest;
import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.entity.*;
import com.lufthansa.tripcrud.exception.AttachFlightException;
import com.lufthansa.tripcrud.exception.FlightNotFoundException;
import com.lufthansa.tripcrud.exception.NoPermissionException;
import com.lufthansa.tripcrud.exception.TripNotFoundException;
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

    private TripRepository tripRepository;

    private UserRepository userRepository;
    private TripConverter tripConverter;

    private FlightRepository flightRepository;

    private JwtProvider jwtUtils;

    private HttpServletRequest request;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, UserRepository userRepository, TripConverter tripConverter, FlightRepository flightRepository, JwtProvider jwtUtils, HttpServletRequest request) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.tripConverter = tripConverter;
        this.flightRepository = flightRepository;
        this.jwtUtils = jwtUtils;
        this.request = request;
    }

    @Override
    public List<TripDto> findAll() {
        String headerAuth = request.getHeader("Authorization");
        String jwt = headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        return tripRepository.findAllByUserUsername(username).stream().map(trip -> tripConverter.convertToDto(trip)).collect(Collectors.toList());
    }

    @Override
    public List<TripDto> findTripByStatus(@PathVariable TripStatusEnum status) {
        String headerAuth = request.getHeader("Authorization");
        String jwt = headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        return tripRepository.findByStatusAndUserUsername(status, username).stream().map(trip -> tripConverter.convertToDto(trip)).collect(Collectors.toList());
    }

    @Override
    public Trip createTrip(TripDto tripDto) {

        String headerAuth = request.getHeader("Authorization");
        String jwt = headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User user = userRepository.findByUsername(username);

        Trip trip = new Trip(user, tripDto.getDescription(), tripDto.getOrigin(), tripDto.getDestination(), TripStatusEnum.CREATED, tripDto.getDepartureDate(), tripDto.getArrivalDate(), tripDto.getTripReason());

        tripRepository.save(trip);

        user.getTrip().add(trip);

        return trip;
    }

    @Override
    public void deleteTrip(Long tripId) throws TripNotFoundException {

        String headerAuth = request.getHeader("Authorization");
        String jwt = headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        User user = userRepository.findByUsername(username);
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new TripNotFoundException(tripId));

        if(!trip.getUser().getUsername().equals(username)){
            throw new NoPermissionException();
        }

        Flight flight = trip.getFlight();

        flight.getTrip().remove(trip);
        user.getTrip().remove(trip);
        tripRepository.deleteById(tripId);

    }


    @Override
    public void updateTrip(TripDto tripDto) throws TripNotFoundException {

        Trip trip = tripRepository.findById(tripDto.getId()).orElseThrow(() -> new TripNotFoundException(tripDto.getId()));

        String headerAuth = request.getHeader("Authorization");
        String jwt = headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        if(!trip.getUser().getUsername().equals(username)){
            throw new NoPermissionException();
        }

        trip.setDescription(tripDto.getDescription());
        trip.setOrigin(tripDto.getOrigin());
        trip.setDestination(tripDto.getDestination());
        trip.setDepartureDate(tripDto.getDepartureDate());
        trip.setArrivalDate(tripDto.getArrivalDate());
        trip.setReason(tripDto.getTripReason());
        tripRepository.save(trip);

    }


    @Override
    public void updateStatus(Long id, TripStatusEnum status) throws TripNotFoundException {

        Optional<Trip> trip = tripRepository.findById(id);

        if (trip.isPresent()) {
            if(status.equals(TripStatusEnum.APPROVED) && !trip.get().getStatus().equals(TripStatusEnum.WAITING_FOR_APPROVAL)) {
                throw new RuntimeException("Trip is not in approval stage.");
            }

            trip.get().setStatus(status);
            tripRepository.save(trip.get());
        } else {
            throw new TripNotFoundException(id);
        }
    }

    @Override
    public void attachFlight(AttachFlightRequest attachFlightRequest) {
        Trip trip = tripRepository.findById(attachFlightRequest.getTripId()).orElseThrow(() -> new TripNotFoundException(attachFlightRequest.getTripId()));

        String headerAuth = request.getHeader("Authorization");
        String jwt = headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        if(!trip.getUser().getUsername().equals(username)){
            throw new NoPermissionException();
        }

        if (trip.getStatus().equals(TripStatusEnum.APPROVED)) {
            Flight flight = flightRepository.findById(attachFlightRequest.getFlightId()).orElseThrow(() -> new FlightNotFoundException(attachFlightRequest.getFlightId()));
            trip.setFlight(flight);
            tripRepository.save(trip);
        } else {
            throw new AttachFlightException("Cannot add flight to a non Approved trip");
        }
    }

}
