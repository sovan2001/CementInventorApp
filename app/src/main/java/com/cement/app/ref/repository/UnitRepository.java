package com.cement.app.ref.repository;

import com.cement.app.ref.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    
    List<Unit> findByEnterpriseIdAndStatus(Long enterpriseId, String status);
}