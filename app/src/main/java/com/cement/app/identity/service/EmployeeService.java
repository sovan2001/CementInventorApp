package com.cement.app.identity.service;

import com.cement.app.identity.dto.AssignBranchRequest;
import com.cement.app.identity.dto.AssignRoleRequest;
import com.cement.app.identity.dto.CreateEmployeeRequest;
import com.cement.app.identity.entity.Employee;
import com.cement.app.identity.entity.EmployeeBranch;
import com.cement.app.identity.entity.EmployeeRole;
import com.cement.app.identity.repository.EmployeeBranchRepository;
import com.cement.app.identity.repository.EmployeeRepository;
import com.cement.app.identity.repository.EmployeeRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeRoleRepository employeeRoleRepository;
    private final EmployeeBranchRepository employeeBranchRepository;

    private static final Long DEMO_ENTERPRISE_ID = 1L;

    // =============================
    // GENERATE NEXT EMPLOYEE CODE
    // =============================
    private String generateNextEmployeeCode() {
        Employee lastEmployee = employeeRepository
                .findLastEmployeeByEnterpriseId(DEMO_ENTERPRISE_ID)
                .orElse(null);

        if (lastEmployee == null || lastEmployee.getEmployeeCode() == null) {
            return "EMP001";
        }

        try {
            // Extract numeric part from last employee code (e.g., "EMP001" -> 1)
            String lastCode = lastEmployee.getEmployeeCode();
            String numericPart = lastCode.replaceAll("[^0-9]", "");
            int lastNumber = Integer.parseInt(numericPart);
            int nextNumber = lastNumber + 1;
            
            // Format with leading zeros (e.g., 2 -> "EMP002")
            return String.format("EMP%03d", nextNumber);
        } catch (Exception e) {
            // If parsing fails, start from EMP001
            return "EMP001";
        }
    }

    // =============================
    // CREATE EMPLOYEE
    // =============================
    @Transactional
    public Employee createEmployee(CreateEmployeeRequest request) {

        String employeeCode = generateNextEmployeeCode();

        Employee employee = new Employee();
        employee.setEnterpriseId(DEMO_ENTERPRISE_ID);
        employee.setEmployeeCode(employeeCode);
        employee.setName(request.getName());
        employee.setUsername(request.getUsername());
        employee.setPassword(request.getPassword()); // later encrypt
        employee.setStatus("ACTIVE");

        return employeeRepository.save(employee);
    }

    // =============================
    // ASSIGN ROLE
    // =============================
    @Transactional
    public void assignRole(Long employeeId, AssignRoleRequest request) {

        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setEnterpriseId(DEMO_ENTERPRISE_ID);
        employeeRole.setEmployeeId(employeeId);
        employeeRole.setRoleId(request.getRoleId());
        employeeRole.setStatus("ACTIVE");

        employeeRoleRepository.save(employeeRole);
    }

    // =============================
    // ASSIGN BRANCH WITH ROLE
    // =============================
    @Transactional
    public void assignBranch(Long employeeId, AssignBranchRequest request) {

        EmployeeBranch employeeBranch = new EmployeeBranch();
        employeeBranch.setEnterpriseId(DEMO_ENTERPRISE_ID);
        employeeBranch.setEmployeeId(employeeId);
        employeeBranch.setBranchId(request.getBranchId());
        employeeBranch.setRoleId(request.getRoleId());
        employeeBranch.setStatus("ACTIVE");

        employeeBranchRepository.save(employeeBranch);
    }
    
    // Method to get Next Employee code for new employee registration
    public String getNextEmployeeCode() {
        return generateNextEmployeeCode();
    }
}