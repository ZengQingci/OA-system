package com.object.oasystem.controller;

import com.object.oasystem.model.Employee;
import com.object.oasystem.service.GlobalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class GlobalController {
    @Resource
    private GlobalService globalService;
    @GetMapping("/to_login")
    public String to_login(){
        return "pages/login";
    }
    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String sn, @RequestParam String password){
        Employee employee = globalService.login(sn,password);
        if(employee == null){
            return "redirect:to_login";
        }
        session.setAttribute("loginUser",employee);
        return "redirect:self";
    }
    @GetMapping("/self")
    public String self(){
        return "pages/self";
    }
    @GetMapping("/quit")
    public String quit(HttpSession session){
        session.setAttribute("loginUser",null);
        return "redirect:to_login";
    }

    @GetMapping("/to_change_password")
    public String to_change_password(){
        return "pages/change_password";
    }
    @PostMapping("/change_password")
    public String change_password(HttpSession session, @RequestParam String old, @RequestParam String new1,@RequestParam String new2){
        Employee employee = (Employee)session.getAttribute("loginUser");
        if(employee.getPassword().equals(old)){
            if(new1.equals(new2)){
                employee.setPassword(new1);
                globalService.changePassword(employee);
                return "redirect:self";
            }
        }
        return "redirect:to_change_password";
    }
}
