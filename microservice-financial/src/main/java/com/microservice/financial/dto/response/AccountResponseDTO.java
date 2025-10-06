package com.microservice.financial.dto.response;

import com.microservice.financial.client.get.UserGetDto;
import com.microservice.financial.enums.AccountType;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class AccountResponseDTO {
    private Long id;
    private String name;
    private Float balance;
    private AccountType type;
    private List<TransactionResponseDTO> transactions;
    private UserGetDto user;
    private Date createdAt;
    private Date updatedAt;
}
