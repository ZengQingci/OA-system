package com.object.oasystem.service.impl;

import com.object.oasystem.mapper.DepartmentMapper;
import com.object.oasystem.model.Department;
import com.object.oasystem.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Override
    public Department getDepartment(String sn) {
        return departmentMapper.getDepartment(sn);
    }

    @Override
    public void add(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void edit(Department department) {
        departmentMapper.update(department);
    }

    @Override
    public void remove(String sn) {
        departmentMapper.delete(sn);
    }

    @Override
    public List<Department> getAll() {
        return departmentMapper.selectAll();
    }
}
