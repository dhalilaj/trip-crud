package com.lufthansa.tripcrud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
    @Column(name = "trip_reason")
    @Enumerated(EnumType.STRING)
    private TripReasonEnum reason;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TripStatusEnum status;

    @NotNull
    @Column(name = "departure_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate departure_date;

    @NotNull
    @Column(name = "arrival_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate arrival_date;


    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @ManyToOne(targetEntity = Flight.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    public Trip(User user, String description, String origin, String destination, TripStatusEnum status, LocalDate departure_date, LocalDate arrival_date, TripReasonEnum reason) {

        this.user = user;
        this.description = description;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.reason = reason;
    }
}
