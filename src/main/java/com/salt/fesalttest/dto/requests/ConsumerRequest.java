package com.salt.fesalttest.dto.requests;

import lombok.Data;

import java.util.Date;

@Data
public class ConsumerRequest {
    private String name;

    private String city;

    private String address;

    private String province;

    private String registrationDate;

    private String status;
}
