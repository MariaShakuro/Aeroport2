package com.aviation.core.controller;


import com.aviation.core.dto.FlexibleDataDto;
import com.aviation.core.entity.FlexibleData;
import com.aviation.core.service.BookingService;

import com.aviation.core.service.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flexibledata")
public class BookingController {
    private static final Logger log = LoggerFactory.getLogger(BookingController.class);
    @Autowired
    BookingService bookingService;

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<FlexibleDataDto> getBookingByPhoneNumber(@PathVariable String phoneNumber) {
        log.info("Received request to get booking by phone number: {}", phoneNumber);
        FlexibleDataDto flexibleDataDto = bookingService.findInfoBooking(phoneNumber);
        if (flexibleDataDto != null) return new ResponseEntity<>(flexibleDataDto, HttpStatus.OK);
         else throw new EntityNotFoundException("Booking not found for phone number: " + phoneNumber);
    }

}