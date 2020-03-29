package com.object.oasystem.model;

public class ClaimVoucher {
    private Integer id;
    private String cause;
    private String create_sn;
    private String create_tem;
    private String next_deal_sn;
    private double total_amount;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCreate_sn() {
        return create_sn;
    }

    public void setCreate_sn(String create_sn) {
        this.create_sn = create_sn;
    }

    public String getCreate_tem() {
        return create_tem;
    }

    public void setCreate_tem(String create_tem) {
        this.create_tem = create_tem;
    }

    public String getNext_deal_sn() {
        return next_deal_sn;
    }

    public void setNext_deal_sn(String next_deal_sn) {
        this.next_deal_sn = next_deal_sn;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
