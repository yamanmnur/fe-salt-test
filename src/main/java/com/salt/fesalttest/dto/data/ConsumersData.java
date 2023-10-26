package com.salt.fesalttest.dto.data;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ConsumersData {
    private UUID id;

    private String name;

    private String city;

    private String address;

    private String province;

    private String registrationDate;

    private String status;
}
