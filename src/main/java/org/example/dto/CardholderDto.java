package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CardholderDto {
    private Long cardholderId;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Long accountId;
}

