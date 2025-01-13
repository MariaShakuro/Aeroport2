package com.aviation.core.repository;

import com.aviation.core.entity.TicketEntity;
import com.aviation.core.service.TicketService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
/*
@DataJpaTest
class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @MockBean
    private TicketService ticketService;

    private TicketEntity ticket;

    @BeforeEach
    void setUp() {
        ticket = new TicketEntity();
        ticket.setPassengerName("Maria");
        ticket.setPassengerSurname("Shakuro");
        ticket.setPassengerAge(30);
        ticket.setTicketPrice(100.0);
        ticket.setCityOfRegistration("New York");
        ticket.setCityOfDestination("Los Angeles");
        ticket.setSeat("12A");
        ticket.setCarrierFlight("NY123");
        ticket.setBoardingTime(LocalDateTime.now());
        ticket.setDepartureTime(LocalDateTime.now().plusHours(2));
        ticket.setTerminal(1);
        ticket.setClassOfSeat('A');
        ticket.setBookingCode("ABC123");
        ticket.setBaggageIdNumber(45L);
    }
   //Тест для нахождения сохраненного билета по фамилии и имени пассажира
    @Test
    void testFindByPassengerSurnameAndPassengerName() {
        ticketRepository.save(ticket);
        TicketEntity foundTicket = ticketRepository.findByPassengerSurnameAndPassengerName("Doe", "John");

        assertThat(foundTicket).isNotNull();
        assertThat(foundTicket.getPassengerName()).isEqualTo(ticket.getPassengerName());
        assertThat(foundTicket.getPassengerSurname()).isEqualTo(ticket.getPassengerSurname());
    }
}
*/