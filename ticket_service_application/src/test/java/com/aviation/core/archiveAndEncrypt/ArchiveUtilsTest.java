package com.aviation.core.archiveAndEncrypt;

import com.github.junrar.exception.RarException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
/*
class ArchiveUtilsTest {

    private static final String TEST_DIR = "testDir";
    private static final String TEST_FILE = "testDir/testFile.txt";
    private static final String ZIP_FILE = "testDir/testFile.zip";
    private static final String RAR_FILE = "testDir/testFile.rar";
    private static final String EXTRACT_DIR = "testDir/extracted";

    //Создание тестового окружения
    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
        Files.write(Paths.get(TEST_FILE), "Test content".getBytes());
    }

    //Удаление тестового окружения
    @AfterEach
    void tearDown() throws IOException {
        Files.walk(Paths.get(TEST_DIR))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    //Тест для архивирования файла в ZIP
    @Test
    void testArchiveFileToZip() {
        assertDoesNotThrow(() -> ArchiveUtils.archiveFileToZip(TEST_FILE, ZIP_FILE));
        assertTrue(Files.exists(Paths.get(ZIP_FILE)));
    }

    //Тест для разархивирования ZIP
    @Test
    void testExtractZipFile() throws IOException {
        ArchiveUtils.archiveFileToZip(TEST_FILE, ZIP_FILE);
        assertDoesNotThrow(() -> ArchiveUtils.extractZipFile(ZIP_FILE, EXTRACT_DIR));
        assertTrue(Files.exists(Paths.get(EXTRACT_DIR + "/testFile.txt")));
    }

    //Тест для проверки UnsupportedOperationException при попытке архивировать
    @Test
    void testArchiveFileToRarUnsupported() {
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            ArchiveUtils.archiveFileToRar(TEST_FILE, RAR_FILE);
        });
        assertEquals("Creating RAR archives is not supported by junrar.", exception.getMessage());
    }

    //Тест для разархивирования RAR
    @Test
    void testExtractRarFile() throws IOException, RarException {
        FileInputStream fis = new FileInputStream(RAR_FILE);
        assertDoesNotThrow(() -> ArchiveUtils.extractRarFile(RAR_FILE, EXTRACT_DIR));
        assertTrue(Files.exists(Paths.get(EXTRACT_DIR + "/testFile.txt")));
    }
}
*/