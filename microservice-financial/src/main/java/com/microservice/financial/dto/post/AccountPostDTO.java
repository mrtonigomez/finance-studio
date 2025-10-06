package com.microservice.financial.dto.post;

import com.microservice.financial.enums.AccountType;
import lombok.Data;

@Data
public class AccountPostDTO {
    private Long id;
    private String name;
    private Float balance;
    private AccountType type;
    private Long userId;
}
