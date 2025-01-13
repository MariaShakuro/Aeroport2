package com.aviation.core.controller;

import com.aviation.core.dto.FlightDto;
import com.aviation.core.entity.FlightEntity;
import com.aviation.core.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private static final Logger log = LoggerFactory.getLogger(FlightController.class);
    @Autowired
    private FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        log.info("Received request to create flight: {}", flightDto);
        FlightDto createdFlight = flightService.createFlight(flightDto);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        log.info("Received request to delete flight with ID: {}", id);
        flightService.deleteFlight(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        log.info("Received request to update flight with ID: {}, Updates: {}", id, updates);
        FlightDto updatedFlightDto = flightService.updateFlight(id, updates);
        return ResponseEntity.ok(updatedFlightDto);
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        log.info("Received request to get all flights");
        List<FlightDto> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

}

