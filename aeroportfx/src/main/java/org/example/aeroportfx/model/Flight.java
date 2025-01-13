package org.example.aeroportfx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {
    private String flightNumber;
    private String cityOfRegistration;
    private String cityOfDestination;
    private Double ticketPrice;
    private String status;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Flight(){}
    public Flight(String flightNumber,String cityOfRegistration,String cityOfDestination, String status,LocalDateTime departureTime,LocalDateTime arrivalTime ) {
        this.flightNumber = flightNumber;
        this.cityOfRegistration=cityOfRegistration;
        this.cityOfDestination=cityOfDestination;
        this.status = status;
        this.departureTime=departureTime;
        this.arrivalTime=arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public String getCityOfDestination() {
        return cityOfDestination;
    }

    public void setCityOfDestination(String cityOfDestination) {
        this.cityOfDestination = cityOfDestination;
    }

    public String getCityOfRegistration() {
        return cityOfRegistration;
    }

    public void setCityOfRegistration(String cityOfRegistration) {
        this.cityOfRegistration = cityOfRegistration;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}