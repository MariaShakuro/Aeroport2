package org.example.aeroportfx.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
public class Ticket{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketNumber;
    private String passengerName;
    private String passengerSurname;
    private Integer passengerAge;
    private Double ticketPrice;
    private String cityOfRegistration;
    private String cityOfDestination;
    private String seat;
    //Перевозчик+рейс
    private String carrierFlight;

    private LocalDateTime boardingTime;

    private LocalDateTime departureTime;
    private String gate;
    private Integer terminal;
    private Character classOfSeat;
    private String bookingCode;
    private Long baggageIdNumber;

    public Long getTicketNumber(){
        return ticketNumber;
    }
    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setPassengerAge(Integer passengerAge) {
        this.passengerAge = passengerAge;
    }

    public Integer getPassengerAge() {
        return passengerAge;
    }

    public void setTicketNumber(Long ticketNumber){
        this.ticketNumber=ticketNumber;
    }

    public String getPassengerName(){
        return passengerName;
    }
    public void setPassengerName(String passengerName){
        this.passengerName=passengerName;
    }

    public String getPassengerSurname(){
        return passengerSurname;
    }
    public void setPassengerSurname(String passengerSurname){
        this.passengerSurname=passengerSurname;
    }

    public String getCityOfRegistration(){
        return cityOfRegistration;
    }
    public void setCityOfRegistration(String cityOfRegistration){
        this.cityOfRegistration=cityOfRegistration;
    }

    public String getCityOfDestination(){
        return cityOfDestination;
    }
    public void setCityOfDestination(String cityOfDestination){
        this.cityOfDestination=cityOfDestination;
    }

    public String getSeat(){
        return seat;
    }
    public void setSeat(String seat){
        this.seat=seat;
    }

    public String getCarrierFlight(){
        return carrierFlight;
    }
    public void setCarrierFlight(String carrierFlight){
        this.carrierFlight=carrierFlight;
    }

    public LocalDateTime getBoardingTime(){
        return boardingTime;
    }
    public void setBoardingTime(LocalDateTime boardingTime){
        this.boardingTime=boardingTime;
    }

    public LocalDateTime getDepartureTime(){
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departureTime){
        this.departureTime=departureTime;
    }

    public String getGate(){
        return gate;
    }
    public void setGate(String gate){
        this.gate=gate;
    }

    public Integer getTerminal(){
        return terminal;
    }
    public void setTerminal(Integer terminal){
        this.terminal=terminal;
    }

    public Character getClassOfSeat(){
        return classOfSeat;
    }
    public void setClassOfSeat(Character classOfSeat){
        this.classOfSeat=classOfSeat;
    }

    public String getBookingCode(){
        return bookingCode;
    }
    public void setBookingCode(String bookingCode){
        this.bookingCode=bookingCode;
    }

    public Long getBaggageIdNumber(){
        return baggageIdNumber;
    }
    public void setBaggageIdNumber(Long baggageIdNumber){
        this.baggageIdNumber=baggageIdNumber;
    }
    @Override
    public String toString(){
        return "Ticket{" +
                "passengerName=" +passengerName+
                "passengerSurname=" +passengerSurname+
                "cityOfRegistration=" +cityOfRegistration+
                "cityOfDestination=" +cityOfDestination+
                "seat" + seat+
                "carrierFlight=" +carrierFlight+
                "boardingTime=" +boardingTime+
                "departureTime=" +departureTime+
                "gate=" +gate+
                "terminal=" +terminal+
                "classOfSeat=" +classOfSeat+
                "bookingCode=" +bookingCode+
                "baggageNumber=" +baggageIdNumber+
                "}";
    }

}

