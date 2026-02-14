package com.cement.app.identity.controller;

import com.cement.app.identity.dto.AssignBranchRequest;
import com.cement.app.identity.dto.AssignRoleRequest;
import com.cement.app.identity.dto.CreateEmployeeRequest;
import com.cement.app.identity.entity.Employee;
import com.cement.app.identity.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // =============================
    // CREATE EMPLOYEE
    // =============================
    @PostMapping
    public Employee createEmployee(@RequestBody CreateEmployeeRequest request) {
        return employeeService.createEmployee(request);
    }

    // =============================
    // ASSIGN ROLE
    // =============================
    @PostMapping("/{employeeId}/roles")
    public String assignRole(
            @PathVariable Long employeeId,
            @RequestBody AssignRoleRequest request) {

        employeeService.assignRole(employeeId, request);
        return "Role assigned successfully";
    }

    // =============================
    // ASSIGN BRANCH
    // =============================
    @PostMapping("/{employeeId}/branches")
    public String assignBranch(
            @PathVariable Long employeeId,
            @RequestBody AssignBranchRequest request) {

        employeeService.assignBranch(employeeId, request);
        return "Branch assigned successfully";
    }
}
