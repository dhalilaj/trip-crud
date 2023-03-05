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
    private int flight_nr;

    @Column(name = "Origin")
    private String origin;

    @Column(name = "Destination")
    private String destination;

    @Column(name = "departure_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departure_date;

    @Column(name = "arrival_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrival_date;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private Set<Trip> trip;

    public Flight(int flight_nr, String origin, String destination, LocalDate departure_date, LocalDate arrival_date) {
        this.flight_nr = flight_nr;
        this.origin = origin;
        this.destination = destination;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
    }


}
