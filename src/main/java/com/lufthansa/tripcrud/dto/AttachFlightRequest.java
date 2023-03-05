package com.lufthansa.tripcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachFlightRequest {

    Long flight_id;

    Long trip_id;
}
