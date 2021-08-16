package com.hufsSchedule.hufsScheduleSystem.Entity.table;

import com.hufsSchedule.hufsScheduleSystem.Entity.type.DepartmentType;

import javax.persistence.*;

@Table(name = "department")
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private DepartmentType departmentType;
}