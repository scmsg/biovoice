/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity.client;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 车辆管理Entity
 * @author jinxi
 * @version 2018-01-07
 */
public class Trucks extends DataEntity<Trucks> {
	
	private static final long serialVersionUID = 1L;
	private String usePlaceId;			//使用场所ID
	private String plateNumber;		// 车牌号码
	private String driverName;		// 司机
	private String driverContact;		// 联系方式
	private String managerId;		// 车辆管理员
	private String departmentId;		// 部门ID
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public Trucks() {
		super();
	}

	public Trucks(String id){
		super(id);
	}
	
	public String getUsePlaceId() {
		return usePlaceId;
	}

	public void setUsePlaceId(String usePlaceId) {
		this.usePlaceId = usePlaceId;
	}

	@Length(min=0, max=255, message="车牌号码长度必须介于 0 和 255 之间")
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	@Length(min=0, max=64, message="司机长度必须介于 0 和 64 之间")
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	@Length(min=0, max=255, message="联系方式长度必须介于 0 和 255 之间")
	public String getDriverContact() {
		return driverContact;
	}

	public void setDriverContact(String driverContact) {
		this.driverContact = driverContact;
	}
	
	@Length(min=0, max=64, message="车辆管理员长度必须介于 0 和 64 之间")
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	
	@Length(min=0, max=64, message="部门ID长度必须介于 0 和 64 之间")
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}