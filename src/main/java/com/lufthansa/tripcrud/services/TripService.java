package com.lufthansa.tripcrud.services;

import com.lufthansa.tripcrud.dto.AttachFlightRequest;
import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.entity.Trip;
import com.lufthansa.tripcrud.entity.TripStatusEnum;
import com.lufthansa.tripcrud.exception.TripNotFoundException;

import java.util.List;

public interface TripService {

    List<TripDto> findAll();

    List<TripDto> findTripByStatus(TripStatusEnum status);

    void save(TripDto tripDto);

    void deleteTrip(Long id);

    void deleteById(Long id);

    Trip createTrip(TripDto tripDto);

    void updateTrip(TripDto tripDto);

    void updateStatus(Long id, TripStatusEnum status);

    void attachFlight(AttachFlightRequest attachFlightRequest);
}
