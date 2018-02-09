/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 节点分配Entity
 * @author jinxi
 * @version 2017-12-30
 */
public class NodeAllocate extends DataEntity<NodeAllocate> {
	
	private static final long serialVersionUID = 1L;
	private Long companyId;
	private String companyName;		// 公司（单位名称）
	private String modeCooperate;		// 合作方式
	private Date rentTime;		// 起租时间
	private Long rentLong;		// 租期
	private String nodeData;		// 节点集合
	private BigDecimal erntUnit;		// 出租单价
	private BigDecimal quasiUnit;		// 校准单价
	private String gateData;		// 网关集合
	private Date payTime;		// 计划付款时间
	private BigDecimal amount;		// 金额
	private Double deposit;		// 押金
	private String remark;		// 备注
	
	public NodeAllocate() {
		super();
	}

	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public NodeAllocate(String id){
		super(id);
	}

	@Length(min=0, max=255, message="公司（单位名称）长度必须介于 0 和 255 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Length(min=0, max=255, message="合作方式长度必须介于 0 和 255 之间")
	public String getModeCooperate() {
		return modeCooperate;
	}

	public void setModeCooperate(String modeCooperate) {
		this.modeCooperate = modeCooperate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRentTime() {
		return rentTime;
	}

	public void setRentTime(Date rentTime) {
		this.rentTime = rentTime;
	}
	
	public Long getRentLong() {
		return rentLong;
	}

	public void setRentLong(Long rentLong) {
		this.rentLong = rentLong;
	}
	
	@Length(min=0, max=255, message="节点集合长度必须介于 0 和 255 之间")
	public String getNodeData() {
		return nodeData;
	}

	public void setNodeData(String nodeData) {
		this.nodeData = nodeData;
	}
	
	public BigDecimal getErntUnit() {
		return erntUnit;
	}

	public void setErntUnit(BigDecimal erntUnit) {
		this.erntUnit = erntUnit;
	}
	
	public BigDecimal getQuasiUnit() {
		return quasiUnit;
	}

	public void setQuasiUnit(BigDecimal quasiUnit) {
		this.quasiUnit = quasiUnit;
	}
	
	@Length(min=0, max=255, message="网关集合长度必须介于 0 和 255 之间")
	public String getGateData() {
		return gateData;
	}

	public void setGateData(String gateData) {
		this.gateData = gateData;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}