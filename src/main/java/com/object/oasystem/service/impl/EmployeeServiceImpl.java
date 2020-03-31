package com.object.oasystem.service.impl;

import com.object.oasystem.mapper.EmployeeMapper;
import com.object.oasystem.model.Employee;
import com.object.oasystem.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getOne(String sn) {
        return employeeMapper.select(sn);
    }

    @Override
    public void add(Employee employee) {
        employee.setPassword("00000");
        employeeMapper.insert(employee);
    }

    @Override
    public void edit(Employee employee) {
        employeeMapper.update(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public void delete(String sn) {
        employeeMapper.delete(sn);
    }

}
