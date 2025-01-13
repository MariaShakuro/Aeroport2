package com.aviation.core.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
/*
@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //Тест для создания билета
    @Test
    void testCreateTicket() throws Exception {
        String ticketJson = "{\"ticketPrice\":100.0, \"passengerAge\":30}";

        mockMvc.perform(post("/api/tickets/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ticketJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Ticket created successfully"));
    }
    //Тест для удаления билета
    @Test
    void testDeleteTicket() throws Exception {
        mockMvc.perform(delete("/api/tickets/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ticket deleted successfully"));
    }
    //Тест для чтения данных
    @Test
    void testReadData() throws Exception {
        mockMvc.perform(get("/api/tickets/read")
                        .param("filePath", "testPath")
                        .param("fileType", "txt"))
                .andExpect(status().isOk())
                .andExpect(content().string("Data read successfully"));
    }
   //Тест для записи данных
    @Test
    void testWriteData() throws Exception {
        String ticketJson = "{\"ticketPrice\":100.0, \"passengerAge\":30}";

        mockMvc.perform(patch("/api/tickets/write")
                        .param("filePath", "testPath")
                        .param("fileType", "txt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ticketJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Data written successfully"));
    }
    //Тест для обработки данных
    @Test
    void testProcessData() throws Exception {
        mockMvc.perform(put("/api/tickets/process")
                        .param("passengerSurname", "Doe")
                        .param("passengerName", "John")
                        .param("promoCode", "lifegood")
                        .param("useMiles", "true")
                        .param("miles", "100"))
                .andExpect(status().isOk())
                .andExpect(content().string("Data processed successfully.Updated ticket price"));
    }
   //Тест для архивирования и шифрования
    @Test
    void testArchiveAndEncrypt() throws Exception {
        mockMvc.perform(post("/api/tickets/archive-encrypt")
                        .param("sourceFilePath", "testSourcePath")
                        .param("destFilePath", "testDestPath")
                        .param("key", "1234567890123456")
                        .param("archiveFormat", "zip")
                        .param("encryptFirst", "true")
                        .param("archiveFirst", "false"))
                .andExpect(status().isOk())
                .andExpect(content().string("Data archived and encrypted successfully"));
    }
}*/
