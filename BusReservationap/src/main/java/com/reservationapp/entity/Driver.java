package com.reservationapp.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "licence_number", nullable = false, unique = true)
    private String licenceNumber;

    @Column(name = "adhar_number", nullable = false, unique = true)
    private String adharNumber;

    private String address;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;



    // Constructors, getters, and setters
}

