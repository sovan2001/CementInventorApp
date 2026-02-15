package com.cement.app.ref.entity;

import com.cement.app.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ref_unit")
@Getter
@Setter
public class Unit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @Column(name = "unit_code", nullable = false)
    private String unitCode;
}