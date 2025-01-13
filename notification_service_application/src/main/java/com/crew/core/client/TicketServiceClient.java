package com.crew.core.client;

import com.crew.core.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TicketServiceClient {

    private final RestTemplate restTemplate;
    private final String ticketServiceUrl = "http://localhost:8082/api/tickets/findByBaggageIdNumber";

    @Autowired
    public TicketServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ticket getTicketByBaggageIdNumber(String baggageIdNumber) {
        String url = UriComponentsBuilder.fromHttpUrl(ticketServiceUrl)
                .pathSegment(baggageIdNumber)
                .toUriString();

        return restTemplate.getForObject(url, Ticket.class);
    }
}

