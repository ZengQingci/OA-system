package com.object.oasystem.mapper;

import com.object.oasystem.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("DepartmentMapper")
public interface DepartmentMapper {
    Department getDepartment(Integer sn);

    void insert(Department department);
    void update(Department department);
    void delete(String sn);
    List<Department> selectAll();
}
