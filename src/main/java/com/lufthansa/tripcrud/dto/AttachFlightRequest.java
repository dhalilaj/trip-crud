package com.lufthansa.tripcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachFlightRequest {

    Long flightId;

    Long tripId;
}
