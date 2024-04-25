package com.booking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee extends Person {
    private int experience;

    public Employee(String id, String name, String address, int experience) {
        super(id, name, address);
        this.experience = experience;
    }
}
