package com.object.oasystem.mapper;

import com.object.oasystem.model.DealRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DealRecordMapper")
public interface DealRecordMapper {
    void insert(DealRecord dealRecord);
    List<DealRecord> selectByClaimVoucher(int cvid);
}
