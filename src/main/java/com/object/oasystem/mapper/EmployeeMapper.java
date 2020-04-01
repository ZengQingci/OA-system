package com.object.oasystem.mapper;

import com.object.oasystem.model.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("EmployeeMapper")
public interface EmployeeMapper {
    Employee select(String sn);

    void insert(Employee employee);
    void update(Employee employee);
    void delete(String sn);
    List<Employee> selectAll();

    List<Employee> selectByDepartmentAndPost(@Param("dsn") String dsn, @Param("post") String post);
}
