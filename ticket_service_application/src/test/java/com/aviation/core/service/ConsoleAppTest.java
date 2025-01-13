package com.aviation.core.service;

import com.aviation.core.entity.TicketEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;
/*
@SpringBootTest
class ConsoleAppTest {

    @Mock
    private TicketService ticketService;

    private ConsoleApp consoleApp;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        consoleApp = new ConsoleApp(ticketService);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }
   //Тест для проверки создания билета
    @Test
    void testCreateTicket() throws Exception {
        String input = "Shakuro\nMaria\n30\n100.0\nNew York\nLos Angeles\n12A\nNY123\n2025-04-06 12:00:00\n2025-04-06 14:00:00\nGate1\n1\nE\nABC123\n456L\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        consoleApp.run("create");
        verify(ticketService).createTicket(any(TicketEntity.class));
        assertTrue(outContent.toString().contains("Ticket created successfully!"));
    }
    //Тест для проверки экспорта данных
    @Test
    void testExportData() throws Exception {
        String input = "json\nShakuro Maria\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        TicketEntity ticket = new TicketEntity();
        when(ticketService.getTicketBySurnameAndName("Shakuro", "Maria")).thenReturn(ticket);
        consoleApp.run("export");

        verify(ticketService).exportDataToFile(eq("json"), eq(ticket));
        assertTrue(outContent.toString().contains("Data exported successfully to output/ticket.json"));
    }

    //Тест для проверки обновления цены и билета
    @Test
    void testCalculateNewTicketPrice() throws Exception {
        String input = "Shakuro Maria\nyes\nlifegood\nyes\n100\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        TicketEntity ticket = new TicketEntity();
        when(ticketService.updateTicketPrice("Shakuro", "Maria", "lifegood", true, 100)).thenReturn(ticket);
        consoleApp.run("discount");

        verify(ticketService).updateTicketPrice("Shakuro", "Maria", "lifegood", true, 100);
        assertTrue(outContent.toString().contains("Updated ticket price:"));
    }
}*/
