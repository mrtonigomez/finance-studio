package com.microservice.financial.service;

import com.microservice.financial.dto.request.CategoryRequestDTO;
import com.microservice.financial.dto.response.CategoryResponseDTO;
import com.microservice.financial.entities.Category;
import com.microservice.financial.entities.Transaction;
import com.microservice.financial.persistance.CategoryRepository;
import com.microservice.financial.persistance.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Long> {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return repository;
    }

    // GET por ID
    public CategoryResponseDTO getById(Long id) {
        Category category = super.findById(id);
        return mapToResponseDTO(category);
    }

    // GET todos
    public List<CategoryResponseDTO> getAll() {
        return super.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // CREATE
    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        Category category = mapToEntity(dto);
        Category saved = super.save(category);
        return mapToResponseDTO(saved);
    }

    // UPDATE
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto) {
        Category category = mapToEntity(dto);
        Category updated = super.update(id, category);
        return mapToResponseDTO(updated);
    }

    // DELETE
    public void deleteCategory(Long id) {
        super.delete(id);
    }

    // Mappers
    private CategoryResponseDTO mapToResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    private Category mapToEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
