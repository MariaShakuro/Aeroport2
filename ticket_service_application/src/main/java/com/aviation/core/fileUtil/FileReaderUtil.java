package com.aviation.core.fileUtil;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import javax.xml.parsers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.w3c.dom.*;
import com.google.gson.*;

public class FileReaderUtil {

    public static List<String> readTextFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public static Document readXmlFile(String filePath) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new File(filePath));
        } catch (Exception e) {
            throw new IOException("Error reading XML file", e);
        }
    }

    public static <T> T readJsonFile(String filePath, Class<T> clazz) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, clazz);
        }
    }

    public static <T> T readYamlFile(String filePath, Class<T> clazz) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(reader, clazz);
        }
    }
}
