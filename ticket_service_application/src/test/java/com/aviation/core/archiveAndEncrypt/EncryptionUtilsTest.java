package com.aviation.core.archiveAndEncrypt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import javax.crypto.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;
/*
class EncryptionUtilsTest {

    private static final String TEST_KEY = "1234567890123456"; // 16-битный ключ для AES
    private static final String TEST_DATA = "Test data for encryption";
    private static final String TEST_FILE = "testFile.txt";
    private static final String ENCRYPTED_FILE = "encryptedFile.enc";
    private static final String DECRYPTED_FILE = "decryptedFile.txt";

    //Создание тестового окружения
    @BeforeEach
    void setUp() throws IOException {
        Files.write(Paths.get(TEST_FILE), TEST_DATA.getBytes());
    }

     //Удаление тестового окружения
    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
        Files.deleteIfExists(Paths.get(ENCRYPTED_FILE));
        Files.deleteIfExists(Paths.get(DECRYPTED_FILE));
    }

    //Тест для шифрования строки
    @Test
    void testEncrypt() throws Exception {
        String encryptedData = EncryptionUtils.encrypt(TEST_DATA, TEST_KEY);
        assertNotNull(encryptedData);
        assertNotEquals(TEST_DATA, encryptedData);
    }

    //Тест для дешифрования строки
    @Test
    void testDecrypt() throws Exception {
        String encryptedData = EncryptionUtils.encrypt(TEST_DATA, TEST_KEY);
        String decryptedData = EncryptionUtils.decrypt(encryptedData, TEST_KEY);
        assertEquals(TEST_DATA, decryptedData);
    }

    //Тест для шифрования файла
    @Test
    void testEncryptFile() throws Exception {
        File inputFile = new File(TEST_FILE);
        File outputFile = new File(ENCRYPTED_FILE);
        assertDoesNotThrow(() -> EncryptionUtils.encryptFile(inputFile, outputFile, TEST_KEY));
        assertTrue(outputFile.exists());
        assertTrue(outputFile.length() > 0);
    }

    //Тест для дешифрования файла
    @Test
    void testDecryptFile() throws Exception {
        File inputFile = new File(TEST_FILE);
        File encryptedFile = new File(ENCRYPTED_FILE);
        File decryptedFile = new File(DECRYPTED_FILE);

        EncryptionUtils.encryptFile(inputFile, encryptedFile, TEST_KEY);
        assertDoesNotThrow(() -> EncryptionUtils.decryptFile(encryptedFile, decryptedFile, TEST_KEY));

        assertTrue(decryptedFile.exists());
        assertTrue(decryptedFile.length() > 0);

        String decryptedContent = new String(Files.readAllBytes(decryptedFile.toPath()));
        assertEquals(TEST_DATA, decryptedContent);
    }
}
*/