package com.microservice.financial.service;

import com.microservice.financial.dto.post.AccountPostDTO;
import com.microservice.financial.dto.request.AccountRequestDTO;
import com.microservice.financial.dto.response.AccountResponseDTO;
import com.microservice.financial.dto.response.CategoryResponseDTO;
import com.microservice.financial.dto.response.TransactionResponseDTO;
import com.microservice.financial.entities.Account;
import com.microservice.financial.entities.Transaction;
import com.microservice.financial.persistance.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, Long> {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Account, Long> getRepository() {
        return repository;
    }

    public AccountResponseDTO getById(Long id) {
        Account account = super.findById(id); // obtenemos la entidad
        return mapToResponseDTO(account);
    }

    public List<AccountResponseDTO> getAll() {
        return super.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public AccountPostDTO create(AccountRequestDTO dto) {
        Account account = mapToEntity(dto);
        Account saved = super.save(account);
        return mapToPostDTO(saved);
    }

    public AccountPostDTO updateAccount(Long id, AccountRequestDTO dto) {
        Account account = mapToEntity(dto);
        Account updated = super.update(id, account);
        return mapToPostDTO(updated);
    }

    public void delete(Long id) {
        super.delete(id);
    }

    private AccountResponseDTO mapToResponseDTO(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(account.getId());
        dto.setName(account.getName());
        dto.setBalance(account.getBalance());
        dto.setType(account.getType());
        dto.setUserId(account.getUserId());
        dto.setTransactions(account.getTransactions().stream().map(this::mapTransactionDTO).collect(Collectors.toList()));
        dto.setCreatedAt(account.getCreatedAt());
        dto.setUpdatedAt(account.getUpdatedAt());
        return dto;
    }

    private TransactionResponseDTO mapTransactionDTO(Transaction transaction) {
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setId(transaction.getId());
        transactionResponseDTO.setType(transaction.getType());
        transactionResponseDTO.setQuantity(transaction.getQuantity());

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(transaction.getCategory().getId());
        categoryResponseDTO.setName(transaction.getCategory().getName());

        transactionResponseDTO.setCategory(categoryResponseDTO);
        transactionResponseDTO.setNotes(transaction.getNotes());
        transactionResponseDTO.setUpdatedAt(transaction.getUpdatedAt());
        transactionResponseDTO.setCreatedAt(transaction.getCreatedAt());

        return transactionResponseDTO;
    }

    private AccountPostDTO mapToPostDTO(Account account) {
        AccountPostDTO dto = new AccountPostDTO();
        dto.setId(account.getId());
        dto.setName(account.getName());
        dto.setBalance(account.getBalance());
        dto.setType(account.getType());
        dto.setUserId(account.getUserId());
        return dto;
    }

    // Mapper de DTO a entidad
    private Account mapToEntity(AccountRequestDTO dto) {
        Account account = new Account();
        account.setName(dto.getName());
        account.setBalance(dto.getBalance());
        account.setType(dto.getType());
        account.setUserId(dto.getUserId());
        return account;
    }
}
