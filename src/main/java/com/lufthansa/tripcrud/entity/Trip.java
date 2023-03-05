package com.lufthansa.tripcrud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "origin")
    private String origin;

    @NotNull
    @Column(name = "destination")
    private String destination;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name="departure_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departure_date;

    @NotNull
    @Column(name="arrival_date")
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate arrival_date;

    @ManyToOne(targetEntity = TripReason.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="reasonOfTrip", referencedColumnName = "id")
    @Enumerated(EnumType.STRING)
    private TripReasonEnum trip_reason;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="tripUser", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Flight.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="trip_flight", referencedColumnName = "id")
    private Flight flight;


    public Trip(String description, String origin, String destinationon, String status, LocalDate departure_date, LocalDate arrival_date, TripReasonEnum trip_reason) {
        this.description = description;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.trip_reason = trip_reason;
    }

    public Trip(Flight flight, User user, String description, String origin, String destination, String status, LocalDate departure_date, LocalDate arrival_date, TripReasonEnum trip_reason) {
    this.flight=flight;
    this.user=user;
    this.description = description;
    this.origin = origin;
    this.destination = destination;
    this.status = status;
    this.departure_date = departure_date;
    this.arrival_date = arrival_date;
    this.trip_reason = trip_reason;
    }
}
