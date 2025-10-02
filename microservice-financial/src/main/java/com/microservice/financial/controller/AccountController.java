package com.microservice.financial.controller;

import com.microservice.financial.dto.post.AccountPostDTO;
import com.microservice.financial.dto.request.AccountRequestDTO;
import com.microservice.financial.dto.response.AccountResponseDTO;
import com.microservice.financial.service.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountPostDTO> create(@RequestBody AccountRequestDTO account) {
        AccountPostDTO created = accountService.create(account);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getById(@PathVariable Long id) {
        AccountResponseDTO account = accountService.getById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAll() {
        List<AccountResponseDTO> accounts = accountService.getAll();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountPostDTO> update(@PathVariable Long id, @RequestBody AccountRequestDTO account) {
        AccountPostDTO updated = accountService.updateAccount(id, account);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}