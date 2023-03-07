package com.lufthansa.tripcrud.converter;

import com.lufthansa.tripcrud.dto.TripDto;
import com.lufthansa.tripcrud.entity.Trip;
import org.springframework.stereotype.Service;

@Service
public class TripConverter {

    public TripDto convertToDto(Trip trip) {
        TripDto convertedTrip = new TripDto();
        convertedTrip.setId(trip.getId());
        convertedTrip.setDescription(trip.getDescription());
        convertedTrip.setArrivalDate(trip.getArrivalDate());
        convertedTrip.setDepartureDate(trip.getDepartureDate());
        convertedTrip.setOrigin(trip.getOrigin());
        convertedTrip.setDestination(trip.getDestination());
        convertedTrip.setStatus(trip.getStatus());
        convertedTrip.setTripReason(trip.getReason());
        if (trip.getFlight() != null) {
            convertedTrip.setFlightId(trip.getFlight().getId());
        }
        return convertedTrip;
    }

}
