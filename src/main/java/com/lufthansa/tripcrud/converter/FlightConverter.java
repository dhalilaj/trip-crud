package com.lufthansa.tripcrud.converter;

import com.lufthansa.tripcrud.dto.FlightDto;
import com.lufthansa.tripcrud.entity.Flight;
import org.springframework.stereotype.Service;

@Service
public class FlightConverter {

    public FlightDto convertToDto(Flight flight) {

        FlightDto convertedFlight = new FlightDto();
        convertedFlight.setId(flight.getId());
        convertedFlight.setFlight_nr(flight.getFlight_nr());
        convertedFlight.setDeparture_date(flight.getDeparture_date());
        convertedFlight.setArrival_date(flight.getArrival_date());
        convertedFlight.setOrigin(flight.getOrigin());
        convertedFlight.setDestination(flight.getDestination());
        return convertedFlight;

    }
}
