package com.object.oasystem.service;

import com.object.oasystem.model.Employee;

public interface GlobalService {
    Employee login(String sn,String password);
    void changePassword(Employee employee);
}
