package com.lufthansa.tripcrud.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class FlightDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int flight_nr;

    private String origin;

    private String destination;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departure_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrival_date;

}
