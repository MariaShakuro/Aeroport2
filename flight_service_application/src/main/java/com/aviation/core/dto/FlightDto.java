package com.aviation.core.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    private Long id;
    private String flightNumber;
    private String cityOfRegistration;
    private String cityOfDestination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String status;
}