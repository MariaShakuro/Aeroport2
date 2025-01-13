package com.crew.core.controller;

import com.crew.core.client.TicketServiceClient;
import com.crew.core.entity.Ticket;
import com.crew.core.service.SmsService;
import com.crew.core.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

   /* @Autowired
    private EmailService emailService;
    @Autowired
    private TicketServiceClient ticketServiceClient;

    @PostMapping("/send-email")
    public void sendEmail(@RequestParam String baggageIdNumber, @RequestParam String email) {
        emailService.sendTicketInfoByEmail(baggageIdNumber, email); }*/
}

