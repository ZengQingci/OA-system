package com.object.oasystem.model;

public class DealRecord {
    private Integer id;
    private Integer claim_voucher_id;
    private String deal_sn;
    private String deal_time;
    private String deal_way;
    private String deal_result;
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClaim_voucher_id() {
        return claim_voucher_id;
    }

    public void setClaim_voucher_id(Integer claim_voucher_id) {
        this.claim_voucher_id = claim_voucher_id;
    }

    public String getDeal_sn() {
        return deal_sn;
    }

    public void setDeal_sn(String deal_sn) {
        this.deal_sn = deal_sn;
    }

    public String getDeal_time() {
        return deal_time;
    }

    public void setDeal_time(String deal_time) {
        this.deal_time = deal_time;
    }

    public String getDeal_way() {
        return deal_way;
    }

    public void setDeal_way(String deal_way) {
        this.deal_way = deal_way;
    }

    public String getDeal_result() {
        return deal_result;
    }

    public void setDeal_result(String deal_result) {
        this.deal_result = deal_result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
