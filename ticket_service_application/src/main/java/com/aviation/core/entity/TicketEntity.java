package com.aviation.core.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketNumber;
    @Column(name = "passengerName")
    private String passengerName;
    @Column(name = "passengerSurname")
    private String passengerSurname;
    @Column(name = "passengerAge")
    private Integer passengerAge;
    @Column(name = "ticketPrice")
    private Double ticketPrice;
    @Column(name = "cityOfRegistration")
    private String cityOfRegistration;
    @Column(name = "cityOfDestination")
    private String cityOfDestination;
    @Column(name = "seat")
    private String seat;
    //Перевозчик+рейс
    @Column(name = "carrierFlight")
    private String carrierFlight;
    @Column(name = "boardingTime")
    private LocalDateTime boardingTime;
    @Column(name = "departureTime")
    private LocalDateTime departureTime;
    @Column(name = "gate")
    private String gate;
    @Column(name = "terminal")
    private Integer terminal;
    @Column(name = "classOfSeat")
    private Character classOfSeat;
    @Column(name = "bookingCode", unique = true)
    private String bookingCode;
    @Column(name = "baggageIdNumber")
    private Long baggageIdNumber;


}
