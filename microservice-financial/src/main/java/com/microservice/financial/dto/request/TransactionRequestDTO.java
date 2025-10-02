package com.microservice.financial.dto.request;

import com.microservice.financial.enums.TransactionType;
import lombok.Data;

@Data
public class TransactionRequestDTO {
    private Long accountId;
    private TransactionType type;
    private Float quantity;
    private Long categoryId;
    private String notes;
}