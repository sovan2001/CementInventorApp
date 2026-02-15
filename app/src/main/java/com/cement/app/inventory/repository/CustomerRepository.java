package com.cement.app.inventory.repository;

import com.cement.app.inventory.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByEnterpriseId(Long enterpriseId);

    List<Customer> findByEnterpriseIdAndCustomerNameContainingIgnoreCaseOrPhoneContaining(
        Long enterpriseId, String customerName, String phone
    );
}