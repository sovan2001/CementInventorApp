package com.cement.app.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cement.app.identity.entity.EmployeeBranch;

@Repository
public interface EmployeeBranchRepository extends JpaRepository<EmployeeBranch, Long> {
}
