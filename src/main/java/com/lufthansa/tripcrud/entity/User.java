package com.lufthansa.tripcrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Role> role;

    public User(String username, String password, Set<Role> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @OneToMany(mappedBy = "user")
    private Set<Trip> trip;

}
