package com.aviation.core.fileUtil;

import org.junit.jupiter.api.*;
import org.w3c.dom.Document;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
/*
class FileReaderUtilTest {

    private static final String TEST_DIR = "testDir";
    private static final String TEXT_FILE = "testDir/testFile.txt";
    private static final String XML_FILE = "testDir/testFile.xml";
    private static final String JSON_FILE = "testDir/testFile.json";
    private static final String YAML_FILE = "testDir/testFile.yaml";

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));

        // Создание текстового файла
        Files.write(Paths.get(TEXT_FILE), "Test content".getBytes());

        // Создание XML файла
        String xmlContent = "<root><element>Test content</element></root>";
        Files.write(Paths.get(XML_FILE), xmlContent.getBytes());

        // Создание JSON файла
        String jsonContent = "{\"key\":\"Test content\"}";
        Files.write(Paths.get(JSON_FILE), jsonContent.getBytes());

        // Создание YAML файла
        String yamlContent = "key: Test content";
        Files.write(Paths.get(YAML_FILE), yamlContent.getBytes());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(Paths.get(TEST_DIR))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
    //Тест для чтения текстового файла
    @Test
    void testReadTextFile() throws IOException {
        List<String> lines = FileReaderUtil.readTextFile(TEXT_FILE);
        assertEquals(Collections.singletonList("Test content"), lines);
    }
    //Тест для чтения XML файла
    @Test
    void testReadXmlFile() throws IOException {
        Document document = FileReaderUtil.readXmlFile(XML_FILE);
        String content = document.getDocumentElement().getTextContent();
        assertEquals("Test content", content);
    }
    //Тест для чтения JSON файла
    @Test
    void testReadJsonFile() throws IOException {
        Map<String, String> data = FileReaderUtil.readJsonFile(JSON_FILE, Map.class);
        assertEquals("Test content", data.get("key"));
    }
    //Тест для чтения YAML файла
    @Test
    void testReadYamlFile() throws IOException {
        Map<String, String> data = FileReaderUtil.readYamlFile(YAML_FILE, Map.class);
        assertEquals("Test content", data.get("key"));
    }
}*/
