package com.object.oasystem.service.impl;

import com.object.oasystem.global.Contant;
import com.object.oasystem.mapper.ClaimVoucherItemMapper;
import com.object.oasystem.mapper.ClaimVoucherMapper;
import com.object.oasystem.mapper.DealRecordMapper;
import com.object.oasystem.mapper.EmployeeMapper;
import com.object.oasystem.model.ClaimVoucher;
import com.object.oasystem.model.ClaimVoucherItem;
import com.object.oasystem.model.DealRecord;
import com.object.oasystem.model.Employee;
import com.object.oasystem.service.ClaimVoucherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
@Service
public class ClaimVoucherServiceImpl implements ClaimVoucherService {
    @Resource
    private ClaimVoucherMapper claimVoucherMapper;
    @Resource
    private ClaimVoucherItemMapper claimVoucherItemMapper;
    @Resource
    private DealRecordMapper dealRecordMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date());
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherMapper.insert(claimVoucher);

        for(ClaimVoucherItem item:items){
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemMapper.insert(item);
        }
    }

    @Override
    public ClaimVoucher get(int id) {
        return claimVoucherMapper.select(id);
    }

    @Override
    public List<ClaimVoucherItem> getItems(int cvid) {
        return claimVoucherItemMapper.selectByClaimVoucher(cvid);
    }

    @Override
    public List<DealRecord> getRecords(int cvid) {
        return dealRecordMapper.selectByClaimVoucher(cvid);
    }

    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherMapper.selectByCreateSn(sn);
    }

    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherMapper.selectByNextDealSn(sn);
    }

    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherMapper.update(claimVoucher);

        List<ClaimVoucherItem> olds = claimVoucherItemMapper.selectByClaimVoucher(claimVoucher.getId());
        for(ClaimVoucherItem old:olds){
            boolean isHave=false;
            for(ClaimVoucherItem item:items){
                if(item.getId()==old.getId()){
                    isHave=true;
                    break;
                }
            }
            if(!isHave){
                claimVoucherItemMapper.delete(old.getId());
            }
        }
        for(ClaimVoucherItem item:items){
            item.setClaimVoucherId(claimVoucher.getId());
            if(item.getId()>0){
                claimVoucherItemMapper.update(item);
            }else{
                claimVoucherItemMapper.insert(item);
            }
        }

    }

    public void submit(int id) {
        ClaimVoucher claimVoucher = claimVoucherMapper.select(id);
        Employee employee = employeeMapper.select(claimVoucher.getCreateSn());

        claimVoucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
        List<Employee> ds = employeeMapper.selectByDepartmentAndPost(employee.getDepartment_sn(),Contant.POST_FM);
        claimVoucher.setNextDealSn(ds.get(0).getSn());
        claimVoucherMapper.update(claimVoucher);

        DealRecord dealRecord = new DealRecord();
        dealRecord.setDealWay(Contant.DEAL_SUBMIT);
        dealRecord.setDealSn(employee.getSn());
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_SUBMIT);
        dealRecord.setDealTime(new Date());
        dealRecord.setComment("无");
        dealRecordMapper.insert(dealRecord);
    }

    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher = claimVoucherMapper.select(dealRecord.getClaimVoucherId());
        Employee employee = employeeMapper.select(dealRecord.getDealSn());
        dealRecord.setDealTime(new Date());

        if(dealRecord.getDealWay().equals(Contant.DEAL_PASS)){
            if(claimVoucher.getTotalAmount()<=Contant.LIMIT_CHECK || employee.getPost().equals(Contant.POST_GM)){
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNextDealSn(employeeMapper.selectByDepartmentAndPost(null,Contant.POST_CASHIER).get(0).getSn());

                dealRecord.setDealResult(Contant.CLAIMVOUCHER_APPROVED);
            }else{
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_RECHECK);
                claimVoucher.setNextDealSn(employeeMapper.selectByDepartmentAndPost(null,Contant.POST_GM).get(0).getSn());

                dealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            }
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_BACK)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_BACK);
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_REJECT)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_TERMINATED);
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_PAID)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_PAID);
        }

        claimVoucherMapper.update(claimVoucher);
        dealRecordMapper.insert(dealRecord);
    }
}
