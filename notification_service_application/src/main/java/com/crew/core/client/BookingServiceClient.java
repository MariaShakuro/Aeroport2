package com.crew.core.client;


import com.crew.core.entity.FlexibleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingServiceClient {

    private final RestTemplate restTemplate;
    private final String bookingServiceUrl = "http://localhost:8080/api/flexibledata";

    @Autowired
    public BookingServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public FlexibleData getBookingByPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("\\s+", "");
        System.out.println("Запрос данных для номера телефона: " + phoneNumber);
        String url = String.format("%s/%s", bookingServiceUrl, phoneNumber);
        System.out.println("Сформированный URL-адрес: " + url);
        FlexibleData data = restTemplate.getForObject(url, FlexibleData.class);
        System.out.println("Полученные данные: " + (data != null ? data.toString() : "null"));
        return data;
    }

}
