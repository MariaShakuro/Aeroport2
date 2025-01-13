package com.aviation.core;


import com.aviation.core.entity.FlightEntity;
import com.aviation.core.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void run(String... args) throws Exception {
        if (flightRepository.count() == 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


            FlightEntity flight1 = FlightEntity.builder()
                    .flightNumber("FL123")
                    .cityOfRegistration("Moscow")
                    .cityOfDestination("Minsk")
                    .departureTime(LocalDateTime.parse("2025-01-03 15:00:00", formatter))
                    .arrivalTime(LocalDateTime.parse("2025-01-03 17:00:00", formatter))
                    .status("Scheduled")
                    .build();


            FlightEntity flight2 = FlightEntity.builder()
                    .flightNumber("FL123")
                    .cityOfRegistration("Minsk")
                    .cityOfDestination("Moscow")
                    .departureTime(LocalDateTime.parse("2025-01-15 15:00:00", formatter))
                    .arrivalTime(LocalDateTime.parse("2025-01-15 18:00:00", formatter))
                    .status("Scheduled")
                    .build();

            flightRepository.saveAll(Arrays.asList(flight1, flight2));
        }
    }
}
