package com.aviation.core.fileUtil;

import com.aviation.core.entity.TicketEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.yaml.*;

import static org.junit.jupiter.api.Assertions.*;
/*
class FileWriterUtilTest {

    private static final String TEST_DIR = "testDir";
    private static final String TEXT_FILE = "testDir/testFile.txt";
    private static final String XML_FILE = "testDir/testFile.xml";
    private static final String JSON_FILE = "testDir/testFile.json";
    private static final String YAML_FILE = "testDir/testFile.yaml";
    private TicketEntity ticket;

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));


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
        ticket.setClassOfSeat('E');
        ticket.setBookingCode("ABC123");
        ticket.setBaggageIdNumber(49L);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(Paths.get(TEST_DIR))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
    //Тест для записи текстового файла
    @Test
    void testWriteTextFile() throws IOException {
        FileWriterUtil.writeTextFile(TEXT_FILE, ticket);
        List<String> lines = Files.readAllLines(Paths.get(TEXT_FILE));
        assertEquals(ticket.toString(), String.join("\n", lines));
    }
     //Тест для записи XML файла
    @Test
    void testWriteXmlFile() throws IOException {
        FileWriterUtil.writeXmlFile(XML_FILE, ticket);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new File(XML_FILE));
        } catch (Exception e) {
            throw new IOException("Error reading XML file for validation", e);
        }

        assertEquals("ticket", document.getDocumentElement().getNodeName());
        assertEquals(ticket.getPassengerName(), document.getElementsByTagName("passengerName").item(0).getTextContent());
        assertEquals(ticket.getPassengerSurname(), document.getElementsByTagName("passengerSurname").item(0).getTextContent());
        assertEquals(ticket.getPassengerAge(), Integer.parseInt(document.getElementsByTagName("passengerAge").item(0).getTextContent()));
        assertEquals(ticket.getTicketPrice(), Double.parseDouble(document.getElementsByTagName("ticketPrice").item(0).getTextContent()));
        assertEquals(ticket.getCityOfRegistration(), document.getElementsByTagName("cityOfRegistration").item(0).getTextContent());
        assertEquals(ticket.getCityOfDestination(), document.getElementsByTagName("cityOfDestination").item(0).getTextContent());
        assertEquals(ticket.getSeat(), document.getElementsByTagName("seat").item(0).getTextContent());
        assertEquals(ticket.getCarrierFlight(), document.getElementsByTagName("carrierFlight").item(0).getTextContent());
        assertEquals(ticket.getBoardingTime(), LocalDateTime.parse(document.getElementsByTagName("boardingTime").item(0).getTextContent()));
        assertEquals(ticket.getDepartureTime(), LocalDateTime.parse(document.getElementsByTagName("departureTime").item(0).getTextContent()));
        assertEquals(ticket.getTerminal(), Integer.parseInt(document.getElementsByTagName("terminal").item(0).getTextContent()));
        assertEquals(ticket.getClassOfSeat(),document.getElementsByTagName("classOfSeat").item(0).getTextContent().charAt(0));
        assertEquals(ticket.getBookingCode(), document.getElementsByTagName("bookingCode").item(0).getTextContent());
        assertEquals(ticket.getBaggageIdNumber(), Long.parseLong(document.getElementsByTagName("baggageIdNumber").item(0).getTextContent()));
    }

    //Тест для записи JSON файла
    @Test
    void testWriteJsonFile() throws IOException {
        FileWriterUtil.writeJsonFile(JSON_FILE, ticket);

        ObjectMapper objectMapper = new ObjectMapper();
        TicketEntity readTicket = objectMapper.readValue(new File(JSON_FILE), TicketEntity.class);

        assertEquals(ticket.getPassengerName(), readTicket.getPassengerName());
        assertEquals(ticket.getPassengerSurname(), readTicket.getPassengerSurname());
        assertEquals(ticket.getPassengerAge(), readTicket.getPassengerAge());
        assertEquals(ticket.getTicketPrice(), readTicket.getTicketPrice());
        assertEquals(ticket.getCityOfRegistration(), readTicket.getCityOfRegistration());
        assertEquals(ticket.getCityOfDestination(), readTicket.getCityOfDestination());
        assertEquals(ticket.getSeat(), readTicket.getSeat());
        assertEquals(ticket.getCarrierFlight(), readTicket.getCarrierFlight());
        assertEquals(ticket.getBoardingTime(), readTicket.getBoardingTime());
        assertEquals(ticket.getDepartureTime(), readTicket.getDepartureTime());
        assertEquals(ticket.getTerminal(), readTicket.getTerminal());
        assertEquals(ticket.getClassOfSeat(), readTicket.getClassOfSeat());
        assertEquals(ticket.getBookingCode(), readTicket.getBookingCode());
        assertEquals(ticket.getBaggageIdNumber(), readTicket.getBaggageIdNumber());
    }
    //Тест для записи YAML файла
    @Test
    void testWriteYamlFile() throws IOException {
        FileWriterUtil.writeYamlFile(YAML_FILE, ticket);

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TicketEntity readTicket = objectMapper.readValue(new File(YAML_FILE), TicketEntity.class);

        assertEquals(ticket.getPassengerName(), readTicket.getPassengerName());
        assertEquals(ticket.getPassengerSurname(), readTicket.getPassengerSurname());
        assertEquals(ticket.getPassengerAge(), readTicket.getPassengerAge());
        assertEquals(ticket.getTicketPrice(), readTicket.getTicketPrice());
        assertEquals(ticket.getCityOfRegistration(), readTicket.getCityOfRegistration());
        assertEquals(ticket.getCityOfDestination(), readTicket.getCityOfDestination());
        assertEquals(ticket.getSeat(), readTicket.getSeat());
        assertEquals(ticket.getCarrierFlight(), readTicket.getCarrierFlight());
        assertEquals(ticket.getBoardingTime(), readTicket.getBoardingTime());
        assertEquals(ticket.getDepartureTime(), readTicket.getDepartureTime());
        assertEquals(ticket.getTerminal(), readTicket.getTerminal());
        assertEquals(ticket.getClassOfSeat(), readTicket.getClassOfSeat());
        assertEquals(ticket.getBookingCode(), readTicket.getBookingCode());
        assertEquals(ticket.getBaggageIdNumber(), readTicket.getBaggageIdNumber());
    }
}
*/