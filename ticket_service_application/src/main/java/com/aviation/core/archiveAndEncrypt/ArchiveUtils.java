package com.aviation.core.archiveAndEncrypt;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchiveUtils {

    // Метод для архивирования в ZIP
    public static void archiveFileToZip(String sourceFilePath, String zipFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(sourceFilePath)) {

            ZipEntry zipEntry = new ZipEntry(new File(sourceFilePath).getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
        }
    }

    // Метод для разархивирования ZIP
    public static void extractZipFile(String zipFilePath, String destDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                File destFile = new File(destDir, zipEntry.getName());
                destFile.getParentFile().mkdirs();

                try (FileOutputStream fos = new FileOutputStream(destFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                }
            }
        }
    }

    // Метод для архивирования в RAR
    public static void archiveFileToRar(String sourceFilePath, String rarFilePath) {
        // На данный момент junrar не поддерживает создание RAR-архивов
        throw new UnsupportedOperationException("Creating RAR archives is not supported by junrar.");
    }

    // Метод для разархивирования RAR
    public static void extractRarFile(String rarFilePath, String destDir) throws IOException, RarException {
        File rarFile = new File(rarFilePath);
        Archive archive = new Archive(rarFile);
        FileHeader fileHeader;
        while ((fileHeader = archive.nextFileHeader()) != null) {
            File destFile = new File(destDir, fileHeader.getFileNameString().trim());
            destFile.getParentFile().mkdirs();

            try (FileOutputStream fos = new FileOutputStream(destFile)) {
                archive.extractFile(fileHeader, fos);
            }
        }
    }
}


