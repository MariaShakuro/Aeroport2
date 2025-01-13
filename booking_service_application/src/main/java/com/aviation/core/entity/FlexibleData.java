package com.aviation.core.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flexibledata")
public class FlexibleData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
    private String seat;
    private String gate;
    private Integer terminal;
    private String bookingCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
