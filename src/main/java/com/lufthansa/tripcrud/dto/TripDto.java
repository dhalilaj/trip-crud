package com.lufthansa.tripcrud.dto;

import com.lufthansa.tripcrud.entity.TripReasonEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class TripDto {


    private Long flight_id;

    private Long trip_id;


    @NotNull
    private String description;

    @NotNull
    private String origin;

    @NotNull
    private String destination;

    @NotNull
    private String status;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departure_date;

    @NotNull
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate arrival_date;

    //@NotNull
    private TripReasonEnum tripreason;

}
