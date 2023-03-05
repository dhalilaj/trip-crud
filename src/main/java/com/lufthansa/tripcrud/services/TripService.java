package com.lufthansa.tripcrud.services;

import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.entity.Trip;
import com.lufthansa.tripcrud.entity.TripReasonEnum;

import java.time.LocalDate;
import java.util.List;

public interface TripService {

    List<TripDto> findAll();

    List<TripDto> findTripByStatus (String status);

    void save (TripDto tripDto);

    void deleteTrip(Long id);

    Trip createTrip(Long flight_id, String description, String from, String to, String status, LocalDate departure_date,
                    LocalDate arrival_date, TripReasonEnum tripreason);

    Trip updateTripStatus(Long id, String status);

    void updateTrip(Long id,String description, String from, String to, LocalDate departure_date,
                    LocalDate arrival_date, TripReasonEnum tripreason);
}
