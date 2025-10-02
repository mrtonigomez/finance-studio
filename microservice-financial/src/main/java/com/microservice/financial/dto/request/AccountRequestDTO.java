package com.microservice.financial.dto.request;

import com.microservice.financial.enums.AccountType;
import lombok.Data;

@Data
public class AccountRequestDTO {
    private String name;
    private String balance;
    private AccountType type;
    private Long userId;
}
