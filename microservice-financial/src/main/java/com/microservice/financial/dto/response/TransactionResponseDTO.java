package com.microservice.financial.dto.response;

import com.microservice.financial.enums.TransactionType;
import lombok.Data;
import java.util.Date;

@Data
public class TransactionResponseDTO {
    private Long id;
    private AccountResponseDTO account;   // Resumen de la cuenta asociada
    private TransactionType type;
    private Float quantity;
    private CategoryResponseDTO category;        // DTO de categoría
    private String notes;
    private Date createdAt;
    private Date updatedAt;
}
