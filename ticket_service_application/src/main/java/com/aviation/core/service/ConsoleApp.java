package com.aviation.core.service;

import com.aviation.core.TicketServiceApplication;
import com.aviation.core.entity.TicketEntity;
import com.google.zxing.WriterException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/*
@Component
public class ConsoleApp implements CommandLineRunner {

    private final TicketService ticketService;

    public ConsoleApp(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Ticket");
            System.out.println("2. Export Data");
            System.out.println("3. Discount");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                       createTicket(scanner);
                        break;
                    case 2:
                        exportData(scanner);
                         break;
                    case 3:
                          calculateNewTicketPrice(scanner);
                            break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createTicket(Scanner scanner) throws IOException, NoSuchAlgorithmException, WriterException {
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter passenger surname: ");
        String passengerSurname = scanner.nextLine();
        System.out.print("Enter passenger age: ");
        int passengerAge = scanner.nextInt();
        System.out.print("Enter ticket price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter city of registration: ");
        String cityOfRegistration = scanner.nextLine();
        System.out.print("Enter city of destination: ");
        String cityOfDestination = scanner.nextLine();
        System.out.print("Enter a seat: ");
        String seat = scanner.nextLine();
        System.out.print("Enter a carrier flight: ");
        String carrierFlight = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.print("Enter boarding time (yyyy-MM-dd HH:mm:ss): ");
        String boardingTimeStr = scanner.nextLine();
        LocalDateTime boardingTime = LocalDateTime.parse(boardingTimeStr, formatter);

        System.out.print("Enter departure time (yyyy-MM-dd HH:mm:ss): ");
        String departureTimeStr = scanner.nextLine();
        LocalDateTime departureTime = LocalDateTime.parse(departureTimeStr, formatter);

        System.out.print("Enter gate: ");
        String gate = scanner.nextLine();
        System.out.print("Enter terminal: ");
        Integer terminal = scanner.nextInt();
        System.out.print("Enter class of seat(A-fist class/B-business class/E-economy class) : ");
        String classOfSeatStr = scanner.next();
        Character classOfSeat=classOfSeatStr.charAt(0);
        System.out.print("Enter booking code: ");
        String bookingCode = scanner.next();
        if (bookingCode.isEmpty()) { throw new IllegalArgumentException("Booking code cannot be empty"); }
        System.out.print("Enter baggage id number: ");
        long baggageIdNumber = scanner.nextLong();
        scanner.nextLine();


        TicketEntity ticket = new TicketEntity();
        ticket.setPassengerName(passengerName);
        ticket.setPassengerSurname(passengerSurname);
        ticket.setPassengerAge(passengerAge);
        ticket.setTicketPrice(price);
        ticket.setCityOfRegistration(cityOfRegistration);
        ticket.setCityOfDestination(cityOfDestination);
        ticket.setSeat(seat);
        ticket.setCarrierFlight(carrierFlight);
        ticket.setBoardingTime(boardingTime);
        ticket.setDepartureTime(departureTime);
        ticket.setGate(gate);
        ticket.setTerminal(terminal);
        ticket.setClassOfSeat(classOfSeat);
        ticket.setBookingCode(bookingCode);
        ticket.setBaggageIdNumber(baggageIdNumber);
        ticketService.createTicket(ticket);
        System.out.println("Ticket created successfully!");
    }
    private  void exportData(Scanner scanner) throws IOException {
        System.out.print("Enter file type (json/xml/txt/yaml): ");
        String fileType = scanner.nextLine();
        System.out.print("Enter passenger surname and name to export: ");
        String input=scanner.nextLine();
        String[] parts=input.split(" ",2);
        if(parts.length == 2){
            String passengerSurname = parts[0];
            String passengerName = parts[1];
        ticketService.getTicketBySurnameAndName(passengerSurname,passengerName);
        if (  ticketService.getTicketBySurnameAndName(passengerSurname,passengerName) != null) {
            ticketService.exportDataToFile(fileType, ticketService.getTicketBySurnameAndName(passengerSurname,passengerName));
            System.out.println("Data exported successfully to output/ticket." + fileType);
        }
        else {
            System.out.println("Ticket not found with ID: " + passengerSurname);
        }
        }else System.out.println("There are only 2 parts:Surname and Name");
    }
    private void calculateNewTicketPrice(Scanner scanner){
        System.out.print("Enter passenger surname and name: ");
        String input=scanner.nextLine();
        String[] parts=input.split(" ",2);
        if(parts.length == 2){
            String passengerSurname = parts[0];
            String passengerName = parts[1];
        System.out.print("Do you have a promoCode (yes/no)? ");
        String promoCode = "";
        boolean usePromoCode = scanner.nextLine().equalsIgnoreCase("yes");
        if (usePromoCode) {
            System.out.print("Enter your promoCode: ");
            promoCode = scanner.nextLine();
        }
        System.out.print("Do you have miles (yes/no)? ");
        boolean useMiles = scanner.nextLine().equalsIgnoreCase("yes");
        int miles = 0;
        if (useMiles) {
            System.out.print("Enter your count of miles: ");
            miles = scanner.nextInt();
        }
        TicketEntity updatedTicket = ticketService.updateTicketPrice(passengerSurname,passengerName, promoCode, useMiles, miles);
        if (updatedTicket != null) System.out.println("Updated ticket price: " + updatedTicket.getTicketPrice());
        else  System.out.println("Ticket not found for passenger surname and name: " + passengerSurname+passengerName);
    }
    }
}
*/
