package com.object.oasystem.service;

import com.object.oasystem.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getOne(String sn);

    void add(Employee employee);
    void edit(Employee employee);
    void delete(String sn);
    List<Employee> getAll();
}
