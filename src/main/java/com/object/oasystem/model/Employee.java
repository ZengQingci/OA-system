package com.object.oasystem.model;

public class Employee {
    private String sn;
    private String password;
    private String name;
    private String department_sn;
    private String post;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment_sn() {
        return department_sn;
    }

    public void setDepartment_sn(String department_sn) {
        this.department_sn = department_sn;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
