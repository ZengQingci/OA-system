package com.object.oasystem.controller;

import com.object.oasystem.model.Department;
import com.object.oasystem.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;
//    实现部门列表
    @GetMapping("/list")
    public String list(Model model){
        List<Department> departments = departmentService.getAll();
        model.addAttribute("lists",departments);
        System.out.println(model);
        return "pages/department_list";
    }
    @GetMapping("/{sn}")
    public Department getDepartment(String sn){
        return departmentService.getDepartment(sn);
    }
//    跳转到添加页面
    @GetMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("department",new Department());
        return "pages/department_add";
    }
//    实现部门信息添加
    @PostMapping("/add")
    public String add(Department department){
        departmentService.add(department);
        return "redirect:list";
    }
//    跳转到修改页面
    @GetMapping(value = "/to_update",params = "sn")
    public String toUpdate(String sn,Map<String,Object> map){
        map.put("department",departmentService.getDepartment(sn));
        return "pages/department_update";
    }
//    实现修改
    @PostMapping("/update")
    public String update(Department department){
        departmentService.edit(department);
        return "redirect:list";
    }
//    实现删除
    @RequestMapping(value = "/remove",params = "sn")
    public String remove(String sn){
        System.out.println(sn);
        departmentService.remove(sn);
        return "redirect:list";
    }
}
