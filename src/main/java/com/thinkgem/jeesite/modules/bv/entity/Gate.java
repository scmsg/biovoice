/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 网关信息Entity
 * @author jinxi
 * @version 2017-12-30
 */
public class Gate extends DataEntity<Gate> {
	
	private static final long serialVersionUID = 1L;
	private Long gateId;		// 网关ID
	private String firmwareVersion;		// 固件版本
	private Date enterTime;		// 入库时间
	private String remark;		// 备注
	private String placePosition;		// 摆放位置
	private Integer isAllocated;		// 是否已分配(0:未分配； 1:已分配)
	private String whichCustomer;		// 所属客户
	private String nodeUse;		// 节点用途
	private String simNum;		// SIM卡号
	private Long connetedNodes;		// 连接节点数
	private Long electricity;		// 电量
	
	public Gate() {
		super();
	}

	public Gate(String id){
		super(id);
	}

	public Long getGateId() {
		return gateId;
	}

	public void setGateId(Long gateId) {
		this.gateId = gateId;
	}
	
	@Length(min=0, max=255, message="固件版本长度必须介于 0 和 255 之间")
	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=255, message="摆放位置长度必须介于 0 和 255 之间")
	public String getPlacePosition() {
		return placePosition;
	}

	public void setPlacePosition(String placePosition) {
		this.placePosition = placePosition;
	}
	
	public Integer getIsAllocated() {
		return isAllocated;
	}

	public void setIsAllocated(Integer isAllocated) {
		this.isAllocated = isAllocated;
	}
	
	@Length(min=0, max=255, message="所属客户长度必须介于 0 和 255 之间")
	public String getWhichCustomer() {
		return whichCustomer;
	}

	public void setWhichCustomer(String whichCustomer) {
		this.whichCustomer = whichCustomer;
	}
	
	@Length(min=0, max=255, message="节点用途长度必须介于 0 和 255 之间")
	public String getNodeUse() {
		return nodeUse;
	}

	public void setNodeUse(String nodeUse) {
		this.nodeUse = nodeUse;
	}
	
	@Length(min=0, max=255, message="SIM卡号长度必须介于 0 和 255 之间")
	public String getSimNum() {
		return simNum;
	}

	public void setSimNum(String simNum) {
		this.simNum = simNum;
	}
	
	public Long getConnetedNodes() {
		return connetedNodes;
	}

	public void setConnetedNodes(Long connetedNodes) {
		this.connetedNodes = connetedNodes;
	}
	
	public Long getElectricity() {
		return electricity;
	}

	public void setElectricity(Long electricity) {
		this.electricity = electricity;
	}
	
}