package com.booking.service;


import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.ServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationService {

    List<Customer> customerList;
    List<Employee> employeeList;
    List<Service> serviceList;
    List<Reservation> reservationList;
    Scanner input;

    public ReservationService(List<Customer> customerList, List<Employee> employeeList, List<Service> serviceList, List<Reservation> reservationList) {
        this.customerList =customerList;
        this.employeeList = employeeList;
        this.serviceList = serviceList;
        this.reservationList = reservationList;
        this.input = new Scanner(System.in);
    }

    public String createReservation(List<Customer> customerList, List<Employee> employeeList, List<Service> serviceList) {

        String reservationID = Reservation.generateID(reservationList.size());
        Customer customerInput = null;
        Employee employeeInput = null;
        List<Service> services = new ArrayList<>();
        double reservationPrice = 0;
        String workstage = "In process";

        int num = 1;
        System.out.printf("| %-4s | %-4s | %-20s | %-20s | %-20s | %-20s |\n",
                "No.", "ID", "Nama Customer", "Alamat", "Membership", "Uang");
        System.out.println("+========================================================================================+");


        for (Customer customer : customerList) {
            System.out.printf("| %-4s | %-4s | %-20s | %-20s | %-20s | %-20s |\n",
                    num, customer.getId(), customer.getName(), customer.getAddress(), customer.getMember().getMembershipName(), customer.getWallet());
            num++;
        }

        System.out.println();
        System.out.print("Masukkan Customer ID: ");
        String customerID = input.nextLine();
        System.out.println();

        for (Customer customer : customerList) {
            if (customer.getId().equals(customerID)) {
                customerInput = customer;
            }
        }

        if (customerInput == null) {
            System.out.println("Customer tidak ditemukan");
        }


        num = 1;
        System.out.printf("| %-4s | %-4s | %-20s | %-20s | %-20s |\n",
                "No.", "ID", "Nama Employee", "Alamat", "Pengalaman");
        System.out.println("+========================================================================================+");


        for (Employee employee : employeeList) {

            System.out.printf("| %-4s | %-4s | %-20s | %-20s | %-20s |\n",
                    num, employee.getId(), employee.getName(), employee.getAddress(), employee.getExperience());
            num++;

        }

        System.out.println();
        System.out.print("Masukkan Employee ID: ");
        String employeeID = input.nextLine();
        System.out.println();


        for (Employee employee : employeeList) {
            if (employee.getId().equals(employeeID)) {
                employeeInput = employee;
            }
        }

        if (employeeInput == null) {
            System.out.println("Employee tidak ditemukan");
        }


        num = 1;
        System.out.printf("| %-4s | %-4s | %-20s | %-20s |\n",
                "No.", "ID", "Nama Service", "Harga");
        System.out.println("+========================================================================================+");


        for (Service service : serviceList) {
            System.out.printf("| %-4s | %-4s | %-20s | %-20s |\n",
                    num, service.getServiceId(), service.getServiceName(), service.getPrice());
            num++;
        }

        String tambahService;
        String serviceID;
        double totalPrice = 0;
            do {
                System.out.println();
                System.out.print("Masukkan Service ID: ");
                 serviceID = input.nextLine();
                System.out.println();

                for (Service service : serviceList) {
                    if (service.getServiceId().equals(serviceID)) {
                        services.add((Service) new Service(service.getServiceId(), service.getServiceName(), service.getPrice()));
                        totalPrice = totalPrice + service.getPrice();
                    }
                }

                System.out.print("Pilih service lain? (Y/T)");
                tambahService = input.nextLine();

            } while (tambahService.equals("Y"));



        if (services == null) {
            System.out.println("Service tidak ditemukan");
        }

        if (customerInput.getMember().getMembershipName().equals("Silver")) {
            totalPrice = totalPrice - (totalPrice * 0.05);
        } else if (customerInput.getMember().getMembershipName().equals("Gold")) {
            totalPrice = totalPrice - (totalPrice * 0.1);
        } else {
            totalPrice = totalPrice;
        }

        reservationPrice = totalPrice;
        Reservation newReservation = new Reservation(
                reservationID,
                customerInput,
                employeeInput,
                services,
                reservationPrice,
                workstage
        );
        this.reservationList.add(newReservation);

        System.out.println();
        System.out.println("Booking Berhasil!");
        System.out.print("Total harga: ");
        System.out.println(reservationPrice);
        System.out.println();


        return "";

    }

    public static void getCustomerByCustomerId(){
        
    }

    public String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    public String editReservationWorkstage(List<Reservation> reservationList){

        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");


        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                        num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }

        }

//        Reservation reservation = null;

        System.out.println();
        System.out.print("Masukkan Reservation ID: ");
        String reservationID = input.nextLine();

        System.out.println();
        System.out.print("Ubah status reservasi: ");
        String workstageInput = input.nextLine();

        for (Reservation reservation : reservationList) {
            if(reservation.getReservationId().equals(reservationID)) {
                reservation.setWorkstage(workstageInput);

                System.out.println("Status Reservasi telah diubah!");
            } else {
                System.out.println("Reservasi ID tidak ditemukan");
            }
        }


        return "";
        
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
