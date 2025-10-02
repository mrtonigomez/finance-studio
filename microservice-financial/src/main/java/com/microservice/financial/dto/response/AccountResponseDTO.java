package com.microservice.financial.dto.response;

import com.microservice.financial.enums.AccountType;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class AccountResponseDTO {
    private Long id;
    private String name;
    private String balance;
    private AccountType type;
    private Long userId;
    private List<TransactionResponseDTO> transactions;
    private Date createdAt;
    private Date updatedAt;
}
