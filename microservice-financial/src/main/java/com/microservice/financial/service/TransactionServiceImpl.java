package com.microservice.financial.service;

import com.microservice.financial.dto.request.TransactionRequestDTO;
import com.microservice.financial.dto.response.AccountResponseDTO;
import com.microservice.financial.dto.response.CategoryResponseDTO;
import com.microservice.financial.dto.response.TransactionResponseDTO;
import com.microservice.financial.entities.Account;
import com.microservice.financial.entities.Category;
import com.microservice.financial.entities.Transaction;
import com.microservice.financial.persistance.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl extends BaseServiceImpl<Transaction, Long> {

    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Transaction, Long> getRepository() {
        return repository;
    }

    // GET por id
    public TransactionResponseDTO getById(Long id) {
        Transaction transaction = super.findById(id); // usamos método del BaseService
        return mapToResponseDTO(transaction);
    }

    // GET todos
    public List<TransactionResponseDTO> getAll() {
        return super.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // CREATE
    public TransactionResponseDTO create(TransactionRequestDTO dto) {
        Transaction transaction = mapToEntity(dto);
        Transaction saved = super.save(transaction);
        return mapToResponseDTO(saved);
    }

    // UPDATE
    public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO dto) {
        Transaction transaction = mapToEntity(dto);
        Transaction updated = super.update(id, transaction);
        return mapToResponseDTO(updated);
    }

    // DELETE
    public void deleteTransaction(Long id) {
        super.delete(id);
    }

    // Mapper de entidad a DTO
    private TransactionResponseDTO mapToResponseDTO(Transaction transaction) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setId(transaction.getId());

        // Mapear cuenta asociada
        AccountResponseDTO accountDTO = new AccountResponseDTO();
        accountDTO.setId(transaction.getAccount().getId());
        accountDTO.setName(transaction.getAccount().getName());
        accountDTO.setBalance(transaction.getAccount().getBalance());
        accountDTO.setType(transaction.getAccount().getType());
        dto.setAccount(accountDTO);

        // Mapear categoría
        CategoryResponseDTO categoryDTO = new CategoryResponseDTO();
        if (transaction.getCategory() != null) {
            categoryDTO.setId(transaction.getCategory().getId());
            categoryDTO.setName(transaction.getCategory().getName());
        }
        dto.setCategory(categoryDTO);

        dto.setType(transaction.getType());
        dto.setQuantity(transaction.getQuantity());
        dto.setNotes(transaction.getNotes());
        dto.setCreatedAt(transaction.getCreatedAt());
        dto.setUpdatedAt(transaction.getUpdatedAt());
        return dto;
    }

    // Mapper de DTO a entidad
    private Transaction mapToEntity(TransactionRequestDTO dto) {
        Transaction transaction = new Transaction();

        // Solo seteamos la referencia por id
        Account account = new Account();
        account.setId(dto.getAccountId());
        transaction.setAccount(account);

        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(dto.getCategoryId());
            transaction.setCategory(category);
        }

        transaction.setType(dto.getType());
        transaction.setQuantity(dto.getQuantity());
        transaction.setNotes(dto.getNotes());
        return transaction;
    }
}
