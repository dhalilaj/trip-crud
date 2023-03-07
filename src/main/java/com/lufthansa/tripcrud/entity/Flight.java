package com.lufthansa.tripcrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flightNR", unique = true)
    private int flightNr;

    @Column(name = "Origin")
    private String origin;

    @Column(name = "Destination")
    private String destination;

    @Column(name = "departure_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Trip> trip;

    public Flight(int flightNr, String origin, String destination, LocalDate departureDate, LocalDate arrivalDate) {
        this.flightNr = flightNr;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }


}
