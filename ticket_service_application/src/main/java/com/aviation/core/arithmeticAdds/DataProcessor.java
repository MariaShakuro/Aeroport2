package com.aviation.core.arithmeticAdds;

import java.util.*;
import java.util.regex.*;
import javax.script.ScriptEngineManager;

import com.aviation.core.entity.TicketEntity;


public class DataProcessor {

    // Функция на подсчет общей стоимости билетов
    public static double calculateTotalCost(TicketEntity ticket, String promoCode, boolean useMiles, int miles) {
        double baseCost = ticket.getTicketPrice();
        double discount = 0.0;
        int age = ticket.getPassengerAge();

        if (age < 7) return 0.0;
        else if (age < 18) discount += 0.5;

        // Промокод
        if ("lifegood".equalsIgnoreCase(promoCode)) discount += 0.15;

        // Скидка за мили
        if (useMiles) {
            double milesDiscount = miles * 10;
            baseCost -= milesDiscount;

            if (baseCost < 0) baseCost = 0;
        }

        baseCost *= (1 - discount);

        return baseCost;
    }


    // Замена арифметических операций результатами
    protected static String replaceArithmeticOperations(String content) {
        Pattern pattern = Pattern.compile("([-+/*]?\\d+\\.?\\d*)");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String expression = matcher.group();
            double result = evaluateExpression(expression);
            content = content.replace(expression, String.valueOf(result));
        }
        return content;
    }

    // Метод для оценки арифметических выражений (простой пример)
    private static double evaluateExpression(String expression) {
        try {
            return (double) new ScriptEngineManager().getEngineByName("JavaScript").eval(expression);
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating expression: " + expression, e);
        }
    }

    // Фильтрация данных без использования регулярных выражений
    public static List<String> filterDataWithoutRegex(List<String> data, String filterCriteria) {
        List<String> filteredData = new ArrayList<>();
        for (String line : data) {
            if (line.contains(filterCriteria)) {
                filteredData.add(line);
            }
        }
        return filteredData;
    }

    // Фильтрация данных с использованием регулярных выражений
    public static List<String> filterDataWithRegex(List<String> data, String regex) {
        List<String> filteredData = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        for (String line : data) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                filteredData.add(line);
            }
        }
        return filteredData;
    }

}


