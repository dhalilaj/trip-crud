package com.lufthansa.tripcrud.services;

import com.lufthansa.tripcrud.dto.AttachFlightRequest;
import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.entity.Trip;
import com.lufthansa.tripcrud.entity.TripStatusEnum;
import com.lufthansa.tripcrud.exception.AttachFlightException;
import com.lufthansa.tripcrud.exception.FlightNotFoundException;
import com.lufthansa.tripcrud.exception.TripNotFoundException;

import java.util.List;

public interface TripService {

    List<TripDto> findAll();

    List<TripDto> findTripByStatus(TripStatusEnum status);

    void deleteTrip(Long id) throws TripNotFoundException;


    Trip createTrip(TripDto tripDto);

    void updateTrip(TripDto tripDto) throws TripNotFoundException;

    void updateStatus(Long id, TripStatusEnum status) throws TripNotFoundException;

    void attachFlight(AttachFlightRequest attachFlightRequest) throws FlightNotFoundException, AttachFlightException, TripNotFoundException;
}
