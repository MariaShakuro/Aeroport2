package com.aviation.core.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
/*
class BarcodeGeneratorTest {

    private static final String TEST_TEXT = "Hello, World!";
    private static final String FILE_PATH = "barcodes/test.png";

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get("barcodes"));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(Paths.get("barcodes"))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
    //Тест для генерации штрих-кода и проверки его хэш-суммы
    @Test
    void testGenerateBarcode() throws Exception {
        String hash = BarcodeGenerator.generateBarcode(TEST_TEXT, FILE_PATH);
        assertTrue(Files.exists(Paths.get(FILE_PATH)));
        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }
    //Тест для хэширования файла и проверки получения корректной хэш-суммы
    @Test
    void testHashFile() throws IOException, NoSuchAlgorithmException {
        Files.write(Paths.get(FILE_PATH), "Test content".getBytes());
        String hash = BarcodeGenerator.hashFile(Paths.get(FILE_PATH));
        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }
}*/
