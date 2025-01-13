package com.aviation.core.service;

import com.aviation.core.entity.FlightEntity;
import com.aviation.core.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
/*
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @MockBean
    private FlightRepository flightRepository;

     //Тест для создания полета
    @Test
    public void testCreateFlight() {
        FlightEntity flight = new FlightEntity(1L, "FL123", "Moscow", "Minsk", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Scheduled");

        Mockito.when(flightRepository.save(Mockito.any(FlightEntity.class))).thenReturn(flight);

        FlightEntity createdFlight = flightService.createFlight(flight);

        assertNotNull(createdFlight);
        assertEquals(flight.getFlightNumber(), createdFlight.getFlightNumber());
    }
    //Тест для удаления полета
    @Test
    public void testDeleteFlight() {
        Long flightId = 1L;
        FlightEntity flight = new FlightEntity(flightId, "FL123", "Moscow", "Minsk", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Scheduled");

        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));
        Mockito.doNothing().when(flightRepository).delete(flight);

        flightService.deleteFlight(flightId);

        Mockito.verify(flightRepository, Mockito.times(1)).delete(flight);
    }
    //Тест для получения всех полетов
    @Test
    public void testGetAllFlights() {
        List<FlightEntity> flights = Arrays.asList(
                new FlightEntity(1L, "FL123", "Moscow", "Minsk", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Scheduled"),
                new FlightEntity(2L, "FL124", "Saint Petersburg", "Helsinki", LocalDateTime.now(), LocalDateTime.now().plusHours(3), "Scheduled")
        );

        Mockito.when(flightRepository.findAll()).thenReturn(flights);

        List<FlightEntity> result = flightService.getAllFlights();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
     //Тест для обновления полета
    @Test
    public void testUpdateFlight() {
        Long flightId = 1L;
        Map<String, Object> updates = new HashMap<>();
        updates.put("cityOfRegistration", "Saint Petersburg");

        FlightEntity flight = new FlightEntity(flightId, "FL123", "Moscow", "Minsk", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Scheduled");
        FlightEntity updatedFlight = new FlightEntity(flightId, "FL123", "Saint Petersburg", "Minsk", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Scheduled");

        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));
        Mockito.when(flightRepository.save(flight)).thenReturn(updatedFlight);

        FlightEntity result = flightService.updateFlight(flightId, updates);

        assertNotNull(result);
        assertEquals("Saint Petersburg", result.getCityOfRegistration());
    }
}*/

