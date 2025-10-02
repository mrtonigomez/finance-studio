package com.microservice.financial.dto.post;

import com.microservice.financial.enums.TransactionType;
import lombok.Data;

@Data
public class TransactionPostDTO {
    private Long accountId;
    private TransactionType type;
    private Float quantity;
    private Long categoryId;
    private String notes;
}
