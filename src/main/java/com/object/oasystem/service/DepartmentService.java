package com.object.oasystem.service;

import com.object.oasystem.model.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepartment(Integer sn);
    void add(Department department);
    void edit(Department department);
    void remove(String sn);
    List<Department> getAll();
}
