package com.object.oasystem.controller;

import com.object.oasystem.dto.ClaimVoucherInfo;
import com.object.oasystem.global.Contant;
import com.object.oasystem.model.ClaimVoucher;
import com.object.oasystem.model.DealRecord;
import com.object.oasystem.model.Employee;
import com.object.oasystem.service.ClaimVoucherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {
    @Resource
    private ClaimVoucherService claimVoucherService;
    @GetMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("items", Contant.getItems());
        map.put("info",new ClaimVoucherInfo());
        return "pages/claim_voucher_add";
    }
    @PostMapping("/add")
    public String add(ClaimVoucherInfo info, HttpSession session){
        Employee employee = (Employee)session.getAttribute("loginUser");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherService.save(info.getClaimVoucher(),info.getItems());
        return "redirect:detail?id="+info.getClaimVoucher().getId();
    }
    @GetMapping("/detail")
    public String detail(int id,Map<String,Object> map){
        map.put("claimVoucher",claimVoucherService.get(id));
        map.put("items",claimVoucherService.getItems(id));
        map.put("records",claimVoucherService.getRecords(id));
        return "pages/claim_voucher_detail";
    }

    @GetMapping("/self")
    public String self(HttpSession session, Map<String,Object> map){
        Employee employee = (Employee)session.getAttribute("loginUser");
        map.put("list",claimVoucherService.getForSelf(employee.getSn()));
        return "pages/claim_voucher_self";
    }
    @GetMapping("/deal")
    public String deal(HttpSession session, Map<String,Object> map){
        Employee employee = (Employee)session.getAttribute("loginUser");
        map.put("list",claimVoucherService.getForDeal(employee.getSn()));
        return "pages/claim_voucher_deal";
    }

    @GetMapping("/to_update")
    public String toUpdate(int id,Map<String,Object> map){
        map.put("items", Contant.getItems());
        ClaimVoucherInfo info =new ClaimVoucherInfo();
        info.setClaimVoucher(claimVoucherService.get(id));
        info.setItems(claimVoucherService.getItems(id));
        map.put("info",info);
        return "pages/claim_voucher_update";
    }
    @PostMapping("/update")
    public String update(HttpSession session, ClaimVoucherInfo info){
        Employee employee = (Employee)session.getAttribute("loginUser");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherService.update(info.getClaimVoucher(),info.getItems());
        return "redirect:deal";
    }
    @RequestMapping("/submit")
    public String submit(int id){
        claimVoucherService.submit(id);
        return "redirect:deal";
    }

    @RequestMapping("/to_check")
    public String toCheck(int id,Map<String,Object> map){
        map.put("claimVoucher",claimVoucherService.get(id));
        map.put("items",claimVoucherService.getItems(id));
        map.put("records",claimVoucherService.getRecords(id));
        DealRecord dealRecord =new DealRecord();
        dealRecord.setClaimVoucherId(id);
        map.put("record",dealRecord);
        return "pages/claim_voucher_check";
    }
    @RequestMapping("/check")
    public String check(HttpSession session, DealRecord dealRecord){
        Employee employee = (Employee)session.getAttribute("loginUser");
        dealRecord.setDealSn(employee.getSn());
        claimVoucherService.deal(dealRecord);
        return "redirect:deal";
    }
}
