package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.*;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;
import com.booking.service.PrintService;

public class MenuService {
    private static List<Customer> customerList = PersonRepository.getAllCustomer();
    private static List<Employee> employeeList = PersonRepository.getAllEmployee();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Reservation> reservationList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
        String[] subMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "Show History Reservation", "Back to main menu"};
    
        int optionMainMenu;
        int optionSubMenu;

		boolean backToMainMenu = false;
        boolean backToSubMenu = false;

        PrintService printService = new PrintService(customerList, employeeList);
        ReservationService reservationService = new ReservationService(customerList, employeeList, serviceList, reservationList  );
        ValidationService validationService = new ValidationService();

        do {
            PrintService.printMenu("Main Menu", mainMenuArr);
            optionMainMenu = Integer.valueOf(input.nextLine());
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        optionSubMenu = Integer.valueOf(input.nextLine());
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                                printService.showRecentReservation(reservationList);
                                break;
                            case 2:
                                printService.showAllCustomer(customerList);
                                break;
                            case 3:
                                printService.showAllEmployee(employeeList);
                                break;
                            case 4:
                               printService.showHistoryReservation(reservationList);
                                break;
                            case 0:
                                backToSubMenu = true;
                        }
                    } while (!backToSubMenu);
                    break;
                case 2:
                    reservationService.createReservation(customerList, employeeList, serviceList);
                    break;
                case 3:
                    reservationService.editReservationWorkstage(reservationList);
                    break;
                case 0:
                    backToMainMenu = true;
                    break;
            }
        } while (!backToMainMenu);
		
	}
}
