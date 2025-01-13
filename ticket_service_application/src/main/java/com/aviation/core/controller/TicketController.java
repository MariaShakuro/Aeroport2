package com.aviation.core.controller;


import com.aviation.core.dto.TicketDto;
import com.aviation.core.entity.TicketEntity;
import com.aviation.core.service.TicketService;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);
    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<String> createTicket(@RequestBody TicketDto ticketDto) throws IOException, NoSuchAlgorithmException, WriterException {
            log.info("Received request to create ticket: {}", ticketDto);
            ticketService.createTicket(ticketDto);
            return new ResponseEntity<>("Ticket created successfully", HttpStatus.OK);

    }

    @DeleteMapping("/delete/{ticketNumber}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long ticketNumber) {
            log.info("Received request to delete ticket with number: {}", ticketNumber);
            ticketService.deleteTicket(ticketNumber);
            return new ResponseEntity<>("Ticket deleted successfully", HttpStatus.OK);

    }

    @GetMapping("/findByBaggageIdNumber/{baggageIdNumber}")
    public ResponseEntity<TicketDto> getTicketByBaggageIdNumber(@PathVariable Long baggageIdNumber) {

            log.info("Received request to find ticket by baggage ID number: {}", baggageIdNumber);
            TicketDto ticketDto = ticketService.findByBaggageIdNumber(baggageIdNumber);
                return new ResponseEntity<>(ticketDto, HttpStatus.OK);

    }


    @PatchMapping("/write")
    public ResponseEntity<String> writeData(@RequestParam String filePath, @RequestParam String fileType, @RequestBody TicketDto ticketDto) throws IOException {
            ticketService.writeData(filePath, fileType, ticketDto);
            return new ResponseEntity<>("Data written successfully", HttpStatus.OK);

    }

    @PutMapping("/process")
    public ResponseEntity<String> processData(@RequestParam String passengerSurname, @RequestParam String passengerName, @RequestParam String promoCode,
                                              @RequestParam boolean useMiles, @RequestParam int miles) {
            TicketDto updatedTicket = ticketService.updateTicketPrice(passengerSurname, passengerName, promoCode, useMiles, miles);
                return new ResponseEntity<>("Data processed successfully.Updated ticket price" + updatedTicket.getTicketPrice(), HttpStatus.OK);

    }

    @PostMapping("/archive-encrypt")
    public ResponseEntity<String> archiveAndEncrypt(@RequestParam String sourceFilePath,
                                                    @RequestParam String destFilePath,
                                                    @RequestParam String key,
                                                    @RequestParam String archiveFormat,
                                                    @RequestParam boolean encryptFirst,
                                                    @RequestParam boolean archiveFirst) throws Exception {
            ticketService.archiveAndEncrypt(sourceFilePath, destFilePath, key, archiveFormat, encryptFirst, archiveFirst);
            return new ResponseEntity<>("Data archived and encrypted successfully", HttpStatus.OK);

    }

}


