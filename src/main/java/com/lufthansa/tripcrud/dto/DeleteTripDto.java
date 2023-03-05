package com.lufthansa.tripcrud.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class DeleteTripDto {

    private Long tripId;

}
