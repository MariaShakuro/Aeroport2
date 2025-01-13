package com.aviation.core.service;

import com.aviation.core.dto.TicketDto;
import com.aviation.core.archiveAndEncrypt.ArchiveUtils;
import com.aviation.core.archiveAndEncrypt.EncryptionUtils;
import com.aviation.core.dto.TicketMapper;
import com.aviation.core.entity.TicketEntity;
import com.aviation.core.fileUtil.FileReaderUtil;
import com.aviation.core.fileUtil.FileWriterUtil;
import com.aviation.core.repository.TicketRepository;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import com.aviation.core.arithmeticAdds.DataProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class TicketService {
    private static final Logger log = LoggerFactory.getLogger(TicketService.class);
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketMapper ticketMapper;

    public TicketEntity createTicket(TicketDto ticketDto) throws WriterException, IOException, NoSuchAlgorithmException {
        TicketEntity ticket=ticketMapper.toEntity(ticketDto);
        log.debug("Creating barcode for ticket with booking code: {}", ticket.getBookingCode());
        String barcodeHash = BarcodeGenerator.generateBarcode(ticket.getBookingCode(), "barcodes/" + ticket.getBookingCode() + ".png");
        ticket.setBookingCode(barcodeHash);
        log.debug("Saving ticket to database: {}", ticket);
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long ticketNumber) {
        log.debug("Deleting ticket with number: {}", ticketNumber);
        ticketRepository.deleteById(ticketNumber);
    }

    public void readData(String filePath, String fileType) throws IOException {
        switch (fileType.toLowerCase()) {
            case "txt":
                List<String> textData = FileReaderUtil.readTextFile(filePath);
                break;
            case "xml":
                Document xmlData = FileReaderUtil.readXmlFile(filePath);
                break;
            case "json":
                TicketDto ticketData = FileReaderUtil.readJsonFile(filePath, TicketDto.class);
                break;
            case "yaml":
                TicketDto ticketInfo = FileReaderUtil.readYamlFile(filePath, TicketDto.class);
                break;
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }
    }

    public void writeData(String filePath, String fileType, TicketDto ticketDto) throws IOException {
        TicketEntity ticket=ticketMapper.toEntity(ticketDto);
        switch (fileType.toLowerCase()) {
            case "txt":
                FileWriterUtil.writeTextFile(filePath, ticket);
                break;
            case "xml":
                FileWriterUtil.writeXmlFile(filePath, ticket);
                break;
            case "json":
                FileWriterUtil.writeJsonFile(filePath, ticket);
                break;
            case "yaml":
                FileWriterUtil.writeYamlFile(filePath, ticket);
                break;
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }
    }

    public void processData(String filePath, String fileType, TicketEntity ticket) throws IOException {
        switch (fileType.toLowerCase()) {
            case "txt":
                FileWriterUtil.writeTextFile(filePath, ticket);
                break;
            case "xml":
                FileWriterUtil.writeXmlFile(filePath, ticket);
                break;
            case "json":
                FileWriterUtil.writeJsonFile(filePath, ticket);
                break;
            case "yaml":
                FileWriterUtil.writeYamlFile(filePath, ticket);
                break;
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }
    }

    public TicketDto updateTicketPrice(String passengerSurname, String passengerName, String promoCode, boolean useMiles, int miles) {
        TicketEntity ticket = getTicketBySurnameAndName(passengerSurname, passengerName);
        if (ticket != null) {
            double updatedPrice = DataProcessor.calculateTotalCost(ticket, promoCode, useMiles, miles);
            ticket.setTicketPrice(updatedPrice);
            ticket=ticketRepository.save(ticket);
            return ticketMapper.toDto(ticket);
        }
        return null;
    }

    public TicketDto findByBaggageIdNumber(Long baggageIdNumber) {
        TicketEntity ticket=ticketRepository.findByBaggageIdNumber(baggageIdNumber);
        return ticketMapper.toDto(ticket);
    }

    public void archiveAndEncrypt(String sourceFilePath, String destFilePath, String key, String archiveFormat, boolean encryptFirst, boolean archiveFirst) throws Exception {
        boolean isZip = "zip".equalsIgnoreCase(archiveFormat);
        boolean isRar = "rar".equalsIgnoreCase(archiveFormat);

        if (archiveFirst) {
            if (encryptFirst) {
                if (isZip) {
                    ArchiveUtils.archiveFileToZip(sourceFilePath, destFilePath + ".zip");
                    String encryptedData = EncryptionUtils.encrypt(readFileAsString(destFilePath + ".zip"), key);
                    writeFile(destFilePath, encryptedData);
                } else if (isRar) {
                    ArchiveUtils.archiveFileToRar(sourceFilePath, destFilePath + ".rar");
                    String encryptedData = EncryptionUtils.encrypt(readFileAsString(destFilePath + ".rar"), key);
                    writeFile(destFilePath, encryptedData);
                }
            } else {
                String encryptedData = EncryptionUtils.encrypt(readFileAsString(sourceFilePath), key);
                writeFile(sourceFilePath + ".enc", encryptedData);
                if (isZip) {
                    ArchiveUtils.archiveFileToZip(sourceFilePath + ".enc", destFilePath + ".zip");
                } else if (isRar) {
                    ArchiveUtils.archiveFileToRar(sourceFilePath + ".enc", destFilePath + ".rar");
                }
            }
        } else {
            if (encryptFirst) {
                String encryptedData = EncryptionUtils.encrypt(readFileAsString(sourceFilePath), key);
                writeFile(sourceFilePath + ".enc", encryptedData);
                if (isZip) {
                    ArchiveUtils.archiveFileToZip(sourceFilePath + ".enc", destFilePath + ".zip");
                } else if (isRar) {
                    ArchiveUtils.archiveFileToRar(sourceFilePath + ".enc", destFilePath + ".rar");
                }
            } else {
                if (isZip) {
                    ArchiveUtils.archiveFileToZip(sourceFilePath, destFilePath + ".zip");
                    String encryptedData = EncryptionUtils.encrypt(readFileAsString(destFilePath + ".zip"), key);
                    writeFile(destFilePath, encryptedData);
                } else if (isRar) {
                    ArchiveUtils.archiveFileToRar(sourceFilePath, destFilePath + ".rar");
                }
            }
        }
    }

    public void exportDataToFile(String fileType, TicketDto ticketDto) throws IOException {
        TicketEntity ticket=ticketMapper.toEntity(ticketDto);
        List<TicketEntity> tickets = ticketRepository.findAll();
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        String fileName = "output/ticket." + fileType;
        switch (fileType.toLowerCase()) {
            case "txt":
                FileWriterUtil.writeTextFile(fileName, ticket);
                break;
            case "xml":
                FileWriterUtil.writeXmlFile(fileName, ticket);
                break;
            case "json":
                FileWriterUtil.writeJsonFile(fileName, ticket);
                break;
            case "yaml":
                FileWriterUtil.writeYamlFile(fileName, ticket);
                break;
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }
    }

    private String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    private void writeFile(String filePath, String data) throws IOException {
        Files.write(Paths.get(filePath), data.getBytes());
    }

    public TicketEntity getTicketBySurnameAndName(String passengerSurname, String passengerName) {
        return ticketRepository.findByPassengerSurnameAndPassengerName(passengerSurname, passengerName);
    }
}