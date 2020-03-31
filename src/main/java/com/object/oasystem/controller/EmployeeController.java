package com.object.oasystem.controller;

import com.object.oasystem.global.Contant;
import com.object.oasystem.model.Employee;
import com.object.oasystem.service.DepartmentService;
import com.object.oasystem.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private DepartmentService departmentService;
//    实现员工列表
    @GetMapping("/emps")
    public String emps(Model model){
        List<Employee> emps = employeeService.getAll();
        model.addAttribute("emps",emps);
        return "pages/employee_list";
    }
//   跳转到员工添加页面
    @GetMapping("/to_emps_add")
    public String to_emps_add(Map<String,Object> map){
        map.put("employee",new Employee());
        map.put("dlist",departmentService.getAll());
        map.put("plist",Contant.getPosts());
        return "pages/employee_add";
    }
//    实现员工信息添加
    @PostMapping("/emps_add")
    public String emps_add(Employee employee){
        employeeService.add(employee);
        return "redirect:emps";
    }
    @GetMapping(value = "/to_emps_update",params = "sn")
    public String to_emp_Update(String sn,Map<String,Object> map){
        map.put("employee",employeeService.getOne(sn));
        map.put("dlist",departmentService.getAll());
        map.put("plist", Contant.getPosts());
        return "pages/employee_update";
    }
    @PostMapping("/emps_update")
    public String emps_update(Employee employee){
        employeeService.edit(employee);
        return "redirect:emps";
    }
    @RequestMapping(value = "/emps_remove",params = "sn")
    public String remove(String sn){
        employeeService.delete(sn);
        return "redirect:emps";
    }
}
