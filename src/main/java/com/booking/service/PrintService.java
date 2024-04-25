package com.booking.service;

import java.util.List;

import com.booking.models.*;

public class PrintService {

    List<Customer> customerList;
    List<Employee> employeeList;


    public PrintService(List<Customer> customerList, List<Employee> employeeList) {
        this.customerList = customerList;
        this.employeeList = employeeList;
    }

    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public void showRecentReservation(List<Reservation> reservationList){

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
    }

    public void showAllCustomer(List<Customer> customerList){

        int num = 1;
        System.out.printf("| %-4s | %-4s | %-20s | %-20s | %-20s | %-20s |\n",
                "No.", "ID", "Nama Customer", "Alamat", "Membership", "Uang");
        System.out.println("+========================================================================================+");


        for (Customer customer : customerList) {
            if (customer.getId().contains("Cust")) {
                System.out.printf("| %-4s | %-4s | %-20s | %-20s | %-20s | %-20s |\n",
                        num, customer.getId(), customer.getName(), customer.getAddress(), customer.getMember().getMembershipName(), customer.getWallet());
                num++;
            }

        }

    }

    public void showAllEmployee(List<Employee> employeeList){

        int num = 1;
        System.out.printf("| %-4s | %-4s | %-20s | %-20s | %-20s |\n",
                "No.", "ID", "Nama Employee", "Alamat", "Pengalaman");
        System.out.println("+========================================================================================+");


        for (Employee employee : employeeList) {
            if (employee.getId().contains("Emp")) {
                System.out.printf("| %-4s | %-4s | %-20s | %-20s | %-20s |\n",
                        num, employee.getId(), employee.getName(), employee.getAddress(), employee.getExperience());
                num++;
            }

        }

    }

    public void showHistoryReservation(List<Reservation> reservationList){

        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");


        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Cancel") || reservation.getWorkstage().equalsIgnoreCase("Finish")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                        num, reservation.getReservationId(), reservation.getCustomer().getName(), reservation.getServices().stream(), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }

        }

        System.out.print("Total keuntungan: ");


    }


}
