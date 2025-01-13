package com.crew.core.service;

import com.crew.core.client.BookingServiceClient;
import com.crew.core.entity.FlexibleData;
import com.crew.core.repository.FlexibleDataRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

  // @Value("${twilio.account_sid}")
    private String accountSid ;
  // @Value("${twilio.auth_token}")
    private String authToken;

    @Autowired
    private BookingServiceClient bookingServiceClient;

     @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid,authToken);
    }

    public void sendSms(String to, String from) {
         if(!to.startsWith("+")){
            to="+"+to;
         }
        to = to.replaceAll("\\s+", "");
        System.out.println("Поиск данных по номеру телефона: " + to);
        FlexibleData data = bookingServiceClient.getBookingByPhoneNumber(to);

        if (data != null) {
            String body = String.format("Ваши данные:\nМесто: %s\nВыход: %s\nТерминал: %d\nКод бронирования: %s",
                    data.getSeat(), data.getGate(), data.getTerminal(), data.getBookingCode());

            Message message = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(from),
                    body
            ).create();

            System.out.println("SMS отправлено на номер: " + to);
        } else {
            System.out.println("Данные по номеру телефона не найдены: " + to);
        }
    }
}

