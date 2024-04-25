package com.booking.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services, String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
//        this.reservationPrice = reservationPrice;
        this.workstage = workstage;
    };

    public static String generateID(int input){
        String prefix = "Rsv-";
        String paddedNumber = String.format("%02d", input);
        String id = prefix + paddedNumber;
        return id;
    }

//    public double getReservationPrice() {
////        return calculateReservationPrice();
//    }

//    private double calculateReservationPrice(){
//
//        return 0;
//    }
}
