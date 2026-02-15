package com.cement.app.identity.service;

import com.cement.app.identity.dto.LoginRequest;
import com.cement.app.identity.dto.LoginResponse;
import com.cement.app.identity.entity.Employee;
import com.cement.app.identity.repository.EmployeeRepository;
import com.cement.app.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private static final Long DEFAULT_ENTERPRISE_ID = 1L;

    public LoginResponse login(LoginRequest request) {
        Employee employee = employeeRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // For demo - simple password check (in production, use passwordEncoder.matches())
        if (!employee.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Always set enterprise_id as 1 for Sprint 1 demo
        Long enterpriseId = DEFAULT_ENTERPRISE_ID;

        String token = jwtUtil.generateToken(
            employee.getUsername(), 
            employee.getId(), 
            enterpriseId
        );

        return new LoginResponse(
            token,
            employee.getId(),
            employee.getUsername(),
            employee.getName(),
            enterpriseId
        );
    }
}