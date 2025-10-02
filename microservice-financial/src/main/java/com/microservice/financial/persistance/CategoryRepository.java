package com.microservice.financial.persistance;

import com.microservice.financial.entities.Account;
import com.microservice.financial.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
