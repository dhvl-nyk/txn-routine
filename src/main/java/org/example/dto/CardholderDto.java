package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardholderDto {
    private Long cardholderId;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Long accountId;
}

