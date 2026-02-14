package com.cement.app.identity.entity;

import com.cement.app.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ref_employee_role")
@Getter
@Setter
public class EmployeeRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "role_id", nullable = false)
    private Long roleId;
}
