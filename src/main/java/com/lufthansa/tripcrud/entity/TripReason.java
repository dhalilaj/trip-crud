package com.lufthansa.tripcrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter@Setter@Entity
@Table(name = "tripreason")
public class TripReason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TripReasonEnum tripReason;

    @OneToMany(mappedBy = "trip_reason")
    private Set<Trip> trip = new HashSet<>();
}
