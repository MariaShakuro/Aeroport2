package com.aviation.core.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {
    private Long ticketNumber;
    private String passengerName;
    private String passengerSurname;
    private Integer passengerAge;
    private Double ticketPrice;
    private String cityOfRegistration;
    private String cityOfDestination;
    private String seat;
    private String carrierFlight;//Перевозчик+рейс
    private Date boardingTime;
    private Date departureTime;
    private String gate;
    private Integer terminal;
    private Character classOfSeat;
    private String bookingCode;
    private Long baggageIdNumber;


}
