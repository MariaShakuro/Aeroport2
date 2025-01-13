package com.aviation.core.fileUtil;


import com.aviation.core.entity.TicketEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileWriterUtil {

    public static void writeTextFile(String filePath, TicketEntity ticket) throws IOException {
        try (FileWriter writer = new FileWriter(new File(filePath))) {
            writer.write(ticket.toString());
        }
    }

    public static void writeXmlFile(String filePath, TicketEntity ticket) throws IOException {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Корневой элемент
            org.w3c.dom.Element rootElement = doc.createElement("ticket");
            doc.appendChild(rootElement);

            //Имя Пассажира
            org.w3c.dom.Element passengerName = doc.createElement("passengerName");
            passengerName.appendChild(doc.createTextNode(ticket.getPassengerName()));
            rootElement.appendChild(passengerName);

            //Фамилия Пассажира
            org.w3c.dom.Element passengerSurname = doc.createElement("passengerSurname");
            passengerSurname.appendChild(doc.createTextNode(ticket.getPassengerSurname()));
            rootElement.appendChild(passengerSurname);

            //Возраст Пассажира
            org.w3c.dom.Element passengerAge = doc.createElement("passengerAge");
            passengerAge.appendChild(doc.createTextNode(String.valueOf(ticket.getPassengerAge())));
            rootElement.appendChild(passengerAge);

            //Стоимость билета
            org.w3c.dom.Element ticketPrice = doc.createElement("ticketPrice");
            ticketPrice.appendChild(doc.createTextNode(String.valueOf(ticket.getTicketPrice())));
            rootElement.appendChild(ticketPrice);

            //Город регистрации
            org.w3c.dom.Element cityOfRegistration = doc.createElement("cityOfRegistration");
            cityOfRegistration.appendChild(doc.createTextNode(ticket.getCityOfRegistration()));
            rootElement.appendChild(cityOfRegistration);

            //Город прибытия
            org.w3c.dom.Element cityOfDestination = doc.createElement("cityOfDestination");
            cityOfDestination.appendChild(doc.createTextNode(ticket.getCityOfDestination()));
            rootElement.appendChild(cityOfDestination);

            // Место
            org.w3c.dom.Element seat = doc.createElement("seat");
            seat.appendChild(doc.createTextNode(ticket.getSeat()));
            rootElement.appendChild(seat);

            // Номер рейса
            org.w3c.dom.Element carrierFlight = doc.createElement("carrierFlight");
            carrierFlight.appendChild(doc.createTextNode(ticket.getCarrierFlight()));
            rootElement.appendChild(carrierFlight);

            // Время посадки
            org.w3c.dom.Element boardingTime = doc.createElement("boardingTime");
            boardingTime.appendChild(doc.createTextNode(ticket.getBoardingTime().toString()));
            rootElement.appendChild(boardingTime);

            // Время высадки
            org.w3c.dom.Element departureTime = doc.createElement("departureTime");
            departureTime.appendChild(doc.createTextNode(ticket.getDepartureTime().toString()));
            rootElement.appendChild(departureTime);

            // Терминал
            org.w3c.dom.Element terminal = doc.createElement("terminal");
            terminal.appendChild(doc.createTextNode(String.valueOf(ticket.getTerminal())));
            rootElement.appendChild(terminal);

            // Класс комфорта
            org.w3c.dom.Element classOfSeat = doc.createElement("classOfSeat");
            classOfSeat.appendChild(doc.createTextNode(String.valueOf(ticket.getClassOfSeat())));
            rootElement.appendChild(seat);

            // Код брони
            org.w3c.dom.Element bookingCode = doc.createElement("bookingCode");
            bookingCode.appendChild(doc.createTextNode(ticket.getBookingCode()));
            rootElement.appendChild(bookingCode);

            // Номер багажа
            org.w3c.dom.Element baggageIdNumber = doc.createElement("baggageIdNumber");
            baggageIdNumber.appendChild(doc.createTextNode(ticket.getBaggageIdNumber().toString()));
            rootElement.appendChild(baggageIdNumber);

            // Запись в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new IOException("Error writing XML file", e);
        }
    }

    public static void writeJsonFile(String filePath, TicketEntity ticket) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), ticket);
    }

    public static void writeYamlFile(String filePath, TicketEntity ticket) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), ticket);
    }
}


