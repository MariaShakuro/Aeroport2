package com.aviation.core.service;

import com.aviation.core.dto.FlightDto;
import com.aviation.core.dto.FlightMapper;
import com.aviation.core.entity.FlightEntity;
import com.aviation.core.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class FlightService {
    private static final Logger log = LoggerFactory.getLogger(FlightService.class);
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightMapper flightMapper;

    public FlightDto createFlight(FlightDto flightDto) {
        FlightEntity flight=flightMapper.toEntity(flightDto);
        log.debug("Saving flight to database: {}", flight);
        FlightEntity savedFlight=flightRepository.save(flight);
        return flightMapper.toDto(savedFlight);
    }

    public void deleteFlight(Long flightId) {
        FlightEntity flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        log.debug("Flight found and will be deleted: {}", flight);
        flightRepository.delete(flight);
    }

    public List<FlightDto> getAllFlights() {
         List<FlightEntity> flights = flightRepository.findAll();
         return flights.stream().map(flightMapper::toDto).collect(Collectors.toList());
    }

    public FlightDto updateFlight(Long flightId, Map<String, Object> updates) {
        FlightEntity flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        updates.forEach((key, value) -> {
            switch (key) {
                case "CityOfRegistration":
                    flight.setCityOfRegistration((String) value);
                    break;
                case "CityOfDestination":
                    flight.setCityOfDestination((String) value);
                    break;
                case "departureTime":
                    flight.setDepartureTime((LocalDateTime) value);
                    break;
                case "arrivalTime":
                    flight.setArrivalTime((LocalDateTime) value);
                    break;
                case "status":
                    flight.setStatus((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });
        FlightEntity updatedFlight = flightRepository.save(flight);
        return flightMapper.toDto(updatedFlight);
    }

}



