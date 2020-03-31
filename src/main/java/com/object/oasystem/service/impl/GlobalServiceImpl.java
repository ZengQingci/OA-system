package com.object.oasystem.service.impl;

import com.object.oasystem.mapper.EmployeeMapper;
import com.object.oasystem.model.Employee;
import com.object.oasystem.service.GlobalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class GlobalServiceImpl implements GlobalService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public Employee login(String sn, String password) {
        Employee employee = employeeMapper.select(sn);
        if(employee != null && employee.getPassword().equals(password)){
            return employee;
        }
        return null;
    }

    @Override
    public void changePassword(Employee employee) {
        employeeMapper.update(employee);
    }
}
