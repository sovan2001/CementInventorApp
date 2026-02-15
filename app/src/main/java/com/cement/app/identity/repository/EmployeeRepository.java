package com.cement.app.identity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cement.app.identity.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUsername(String username);
    
    @Query(value = "SELECT * FROM ref_employee WHERE enterprise_id = ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Employee> findLastEmployeeByEnterpriseId(Long enterpriseId);
}