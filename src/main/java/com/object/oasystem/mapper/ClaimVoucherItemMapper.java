package com.object.oasystem.mapper;

import com.object.oasystem.model.ClaimVoucherItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ClaimVoucherItemMapper")
public interface ClaimVoucherItemMapper {
    void insert(ClaimVoucherItem claimVoucherItem);
    void update(ClaimVoucherItem claimVoucherItem);
    void delete(int id);
    List<ClaimVoucherItem> selectByClaimVoucher(int cvid);
}
