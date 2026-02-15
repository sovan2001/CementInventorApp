package com.cement.app.ref.service;

import com.cement.app.common.util.TenantContext;
import com.cement.app.ref.entity.Unit;
import com.cement.app.ref.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    private Long getCurrentTenant() {
        Long tenantId = TenantContext.getTenant();
        return (tenantId != null) ? tenantId : 1L;
    }

    public List<Unit> getAllActiveUnits() {
        return unitRepository.findByEnterpriseIdAndStatus(getCurrentTenant(), "ACTIVE");
    }
}