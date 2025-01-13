package com.aviation.core.service;

import com.aviation.core.dto.FlexibleDataDto;
import com.aviation.core.dto.FlexibleDataMapper;
import com.aviation.core.entity.FlexibleData;
import com.aviation.core.repository.FlexibleDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingService.class);
    @Autowired
    FlexibleDataRepository flexibleDataRepository;
    @Autowired
    FlexibleDataMapper flexibleDataMapper;

    public FlexibleDataDto findInfoBooking(String phoneNumber) {
        log.info("Searching for booking with phone number: {}", phoneNumber);
        FlexibleData flexibleData = flexibleDataRepository.findByPhoneNumber(phoneNumber);
        return flexibleDataMapper.toDto(flexibleData);
    }

}