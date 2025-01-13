package com.aviation.core.archiveAndEncrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.util.Base64;

public class EncryptionUtils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    // Метод для шифрования строки
    public static String encrypt(String data, String key) throws Exception {
        Key aesKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Метод для дешифрования строки
    public static String decrypt(String encryptedData, String key) throws Exception {
        Key aesKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(original);
    }

    // Метод для шифрования файла
    public static void encryptFile(File inputFile, File outputFile, String key) throws Exception {
        performFileOperation(Cipher.ENCRYPT_MODE, inputFile, outputFile, key);
    }

    // Метод для дешифрования файла
    public static void decryptFile(File inputFile, File outputFile, String key) throws Exception {
        performFileOperation(Cipher.DECRYPT_MODE, inputFile, outputFile, key);
    }

    private static void performFileOperation(int cipherMode, File inputFile, File outputFile, String key) throws Exception {
        Key aesKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(cipherMode, aesKey);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            outputStream.write(outputBytes);
        }
    }
}

