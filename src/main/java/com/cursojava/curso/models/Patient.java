package com.cursojava.curso.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@Table(name = "patients")
@ToString @EqualsAndHashCode
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patient_id;
    
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private String date_of_birth;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "address")
    private String address;

    @Column(name = "insurance_number")
    private String insurance_number;

    @Column(name = "date_of_registration")
    private String date_of_resgistration;
  

}
