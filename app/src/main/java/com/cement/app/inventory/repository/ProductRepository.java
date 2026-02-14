package com.cement.app.inventory.repository;

import com.cement.app.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByEnterpriseIdAndStatus(Long enterpriseId, String status);

    boolean existsByEnterpriseIdAndProductNameIgnoreCase(Long enterpriseId, String productName);

}
