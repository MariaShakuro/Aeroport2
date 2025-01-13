package com.crew.core.service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.crew.core.client.TicketServiceClient;
import com.crew.core.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
@Service
public class EmailService {



    private final Properties props;

    @Autowired
    private TicketServiceClient ticketServiceClient;

    public EmailService() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void sendEmailWithAttachment(String to, String subject, String body, File attachment) {
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Создаем текстовую часть сообщения
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Создаем вложение
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(attachment.getName());

            // Собираем сообщение
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email отправлен на адрес: " + to);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendTicketInfoByEmail(String baggageIdNumber, String email) {
        // Получение данных по билету из TicketService по baggageIdNumber
        Ticket ticketData = ticketServiceClient.getTicketByBaggageIdNumber(baggageIdNumber);

        // Создаем временный файл с информацией по билету
        File ticketFile = new File("ticket.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ticketFile))) {
            writer.write("Информация по вашему билету:\n");
            writer.write("Место: " + ticketData.getSeat() + "\n");
            writer.write("Выход: " + ticketData.getGate() + "\n");
            writer.write("Терминал: " + ticketData.getTerminal() + "\n");
            writer.write("Код бронирования: " + ticketData.getBookingCode() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Отправка email с вложением
        String subject = "Ваш билет";
        String body = "Пожалуйста, найдите информацию по вашему билету во вложении.";
        sendEmailWithAttachment(email, subject, body, ticketFile);

        // Удаляем временный файл после отправки
        ticketFile.delete();
    }
}

*/
