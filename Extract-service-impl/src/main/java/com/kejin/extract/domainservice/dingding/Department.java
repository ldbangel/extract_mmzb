package com.kejin.extract.domainservice.dingding;

import java.io.Serializable;

public class Department implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Long parentid;
    private Boolean createDeptGroup;
    private Boolean autoAddUser;

    public Department() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentid() {
        return this.parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Boolean getCreateDeptGroup() {
        return this.createDeptGroup;
    }

    public void setCreateDeptGroup(Boolean createDeptGroup) {
        this.createDeptGroup = createDeptGroup;
    }

    public Boolean getAutoAddUser() {
        return this.autoAddUser;
    }

    public void setAutoAddUser(Boolean autoAddUser) {
        this.autoAddUser = autoAddUser;
    }
}
