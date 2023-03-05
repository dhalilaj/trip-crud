package com.lufthansa.tripcrud.converter;

import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.entity.Trip;
import org.springframework.stereotype.Service;

@Service
public class TripConverter {

    public TripDto convertToDto(Trip trip) {
        TripDto convertedTrip = new TripDto();
        convertedTrip.setId(trip.getId());
        convertedTrip.setFlight_id(trip.getFlight().getId());
        convertedTrip.setDescription(trip.getDescription());
        convertedTrip.setArrival_date(trip.getArrival_date());
        convertedTrip.setDeparture_date(trip.getDeparture_date());
        convertedTrip.setOrigin(trip.getOrigin());
        convertedTrip.setDestination(trip.getDestination());
        convertedTrip.setStatus(trip.getStatus());
        convertedTrip.setTripreason(trip.getReason());
        return convertedTrip;
    }

}
