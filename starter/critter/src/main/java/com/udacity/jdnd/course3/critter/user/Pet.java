package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    private PetType petType;
    private String name;
    private LocalDate birthDate;
    private String notes;
}
