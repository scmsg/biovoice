/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity.client;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 使用场所Entity
 * @author jinxi
 * @version 2018-01-21
 */
public class UsePlace extends DataEntity<UsePlace> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String managerId;		// 负责人
	private String warningPhone;		// 预警电话
	private String alarmPhone;		// 告警电话
	private Integer measuerPeriod;		// 测量周期
	private String departmentId;		// 部门ID
	private String usePlaceId;		// 使用场所ID
	private Integer usePlaceType;		// 使用场所类型
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public UsePlace() {
		super();
	}

	public UsePlace(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="负责人长度必须介于 0 和 64 之间")
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	
	@Length(min=0, max=255, message="预警电话长度必须介于 0 和 255 之间")
	public String getWarningPhone() {
		return warningPhone;
	}

	public void setWarningPhone(String warningPhone) {
		this.warningPhone = warningPhone;
	}
	
	@Length(min=0, max=255, message="告警电话长度必须介于 0 和 255 之间")
	public String getAlarmPhone() {
		return alarmPhone;
	}

	public void setAlarmPhone(String alarmPhone) {
		this.alarmPhone = alarmPhone;
	}
	
	public Integer getMeasuerPeriod() {
		return measuerPeriod;
	}

	public void setMeasuerPeriod(Integer measuerPeriod) {
		this.measuerPeriod = measuerPeriod;
	}
	
	@Length(min=0, max=64, message="部门ID长度必须介于 0 和 64 之间")
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@Length(min=0, max=64, message="使用场所ID长度必须介于 0 和 64 之间")
	public String getUsePlaceId() {
		if(StringUtils.isEmpty(usePlaceId)){
			return id;
		}
		return usePlaceId;
	}

	public void setUsePlaceId(String usePlaceId) {
		if(StringUtils.isEmpty(usePlaceId)){
			this.usePlaceId = id;
		}
		this.usePlaceId = usePlaceId;
	}
	
	public Integer getUsePlaceType() {
		return usePlaceType;
	}

	public void setUsePlaceType(Integer usePlaceType) {
		this.usePlaceType = usePlaceType;
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