package com.object.oasystem.controller;

import com.object.oasystem.model.Department;
import com.object.oasystem.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;
    @GetMapping("/list")
    public String list(Model model){
        List<Department> departments = departmentService.getAll();
        model.addAttribute("lists",departments);
        System.out.println(model);
        return "pages/department_list";
    }
    @GetMapping("/{sn}")
    public Department getDepartment(@PathVariable("sn") Integer sn){
        return departmentService.getDepartment(sn);
    }
}
