package com.aviation.core.arithmeticAdds;

import com.aviation.core.entity.TicketEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
/*
class DataProcessorTest {

    private TicketEntity ticket;

    @BeforeEach
    void setUp() {
        ticket = new TicketEntity();
        ticket.setTicketPrice(100.0);
        ticket.setPassengerAge(30);
    }
    //Тест для подсчета общей стоимости билетов без скидок
    @Test
    void testCalculateTotalCost_NoDiscounts() {
        double cost = DataProcessor.calculateTotalCost(ticket, "", false, 0);
        assertEquals(100.0, cost);
    }
  //Тест для детской скидки(от 7 до 18 лет)
    @Test
    void testCalculateTotalCost_ChildDiscount() {
        ticket.setPassengerAge(10);
        double cost = DataProcessor.calculateTotalCost(ticket, "", false, 0);
        assertEquals(50.0, cost);
    }
    //Тест для младенца(до 7 лет)
    @Test
    void testCalculateTotalCost_Infant() {
        ticket.setPassengerAge(5);
        double cost = DataProcessor.calculateTotalCost(ticket, "", false, 0);
        assertEquals(0.0, cost);
    }
     //Тест для промокода
    @Test
    void testCalculateTotalCost_PromoCode() {
        double cost = DataProcessor.calculateTotalCost(ticket, "lifegood", false, 0);
        assertEquals(85.0, cost);
    }
    //Тест для использования миль
    @Test
    void testCalculateTotalCost_UseMiles() {
        double cost = DataProcessor.calculateTotalCost(ticket, "", true, 5);
        assertEquals(50.0, cost);
    }
    //Тест для всех скидок вместе
    @Test
    void testCalculateTotalCost_AllDiscounts() {
        ticket.setPassengerAge(10);
        double cost = DataProcessor.calculateTotalCost(ticket, "lifegood", true, 5);
        assertEquals(17.5, cost);
    }
   //Тест для фильтрации данных без регулярных выражений
    @Test
    void testFilterDataWithoutRegex() {
        List<String> data = Arrays.asList("apple", "banana", "cherry", "date");
        List<String> result = DataProcessor.filterDataWithoutRegex(data, "a");
        assertEquals(Arrays.asList("apple", "banana", "date"), result);
    }
    //Тест для фильтрации данных с регулярными выражениями
    @Test
    void testFilterDataWithRegex() {
        List<String> data = Arrays.asList("apple", "banana", "cherry", "date");
        List<String> result = DataProcessor.filterDataWithRegex(data, "a.*e");
        assertEquals(Arrays.asList("apple", "date"), result);
    }
    //Тест для замены арифметических операций
    @Test
    void testReplaceArithmeticOperations() {
        String result = DataProcessor.replaceArithmeticOperations("2 + 2");
        assertEquals("4", result);
    }
}
*/