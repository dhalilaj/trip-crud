package com.lufthansa.tripcrud.dto;

import com.lufthansa.tripcrud.entity.TripReasonEnum;
import com.lufthansa.tripcrud.entity.TripStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class TripDto {


    private Long id;

    private Long flightId;

    @NotNull
    private String description;

    @NotNull
    private String origin;

    @NotNull
    private String destination;

    @NotNull
    private TripStatusEnum status;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @NotNull
    private TripReasonEnum tripReason;

}
