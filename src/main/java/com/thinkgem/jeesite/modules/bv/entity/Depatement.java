/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 部门Entity
 * @author jinxi
 * @version 2017-12-30
 */
public class Depatement extends DataEntity<Depatement> {
	
	private static final long serialVersionUID = 1L;
	private String deptName;		// 部门名字
	private String deptHdea;		// 负责人
	private String deadMobile;		// 联系电话
	
	public Depatement() {
		super();
	}

	public Depatement(String id){
		super(id);
	}

	@Length(min=0, max=255, message="部门名字长度必须介于 0 和 255 之间")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Length(min=0, max=255, message="负责人长度必须介于 0 和 255 之间")
	public String getDeptHdea() {
		return deptHdea;
	}

	public void setDeptHdea(String deptHdea) {
		this.deptHdea = deptHdea;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getDeadMobile() {
		return deadMobile;
	}

	public void setDeadMobile(String deadMobile) {
		this.deadMobile = deadMobile;
	}
	
}