package com.object.oasystem.service;

import com.object.oasystem.model.ClaimVoucher;
import com.object.oasystem.model.ClaimVoucherItem;
import com.object.oasystem.model.DealRecord;

import java.util.List;

public interface ClaimVoucherService {
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    ClaimVoucher get(int id);
    List<ClaimVoucherItem> getItems(int cvid);
    List<DealRecord> getRecords(int cvid);

    List<ClaimVoucher> getForSelf(String sn);
    List<ClaimVoucher> getForDeal(String sn);

    void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);
    void submit(int id);
    void deal(DealRecord dealRecord);
}
