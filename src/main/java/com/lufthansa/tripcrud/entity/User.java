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
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;

    @ManyToMany( fetch = FetchType.LAZY )
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role;

    public User(String username, String password, Set<Role> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,  fetch = FetchType.LAZY )
    private Set<Trip> trip;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
