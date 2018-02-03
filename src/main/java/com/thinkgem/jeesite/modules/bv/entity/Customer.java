/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.springframework.beans.factory.annotation.Required;

/**
 * 客户信息Entity
 * @author jinxi
 * @version 2017-12-30
 */
public class Customer extends DataEntity<Customer> {
	
	private static final long serialVersionUID = 1L;
	private String companyName;		// 公司（单位名称）
	private Date startTime;		// 开始时间
	private Date endTime;		// 到期时间
	private Long nodeActive;		// 节点（活跃数）
	private Long nodeBuy;		// 节点（购买数）
	private Long gateActive;		// 网关（活跃数)
	private Long gateBuy;		// 网关（购买数)
	private BigDecimal totalAmount;		// 消费总金额
	private BigDecimal recervable;		// 应收款
	private BigDecimal deposit;		// 押金
	private String customerName;		// 客户名称
	private String adminAccount;		// 根管理账号
	private String adminPassword;		// 根管理密码
	private Integer isBelongCompany;		// 是否&ldquo;集团总公司客户&rdquo;(0:不是；1:是)
	private String provinceName;		// 省份
	private String provinceNo;		// 省份编码
	private String cityName;		// 城市
	private String cityNo;		// 城市编码
	private String districtName;		// 区域
	private String districtNo;		// 区域编码
	private String address;		// 详细地址
	private Integer companyType;		// 单位类型
	private String contact;		// 联系人
	private String mobile;		// 联系电话
	private String remark;		// 备注
	
	public Customer() {
		super();
	}

	public Customer(String id){
		super(id);
	}

	@Length(min=0, max=20, message="公司（单位名称）长度必须介于 0 和 255 之间")
	@Required()
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Long getNodeActive() {
		return nodeActive;
	}

	public void setNodeActive(Long nodeActive) {
		this.nodeActive = nodeActive;
	}
	
	public Long getNodeBuy() {
		return nodeBuy;
	}

	public void setNodeBuy(Long nodeBuy) {
		this.nodeBuy = nodeBuy;
	}
	
	public Long getGateActive() {
		return gateActive;
	}

	public void setGateActive(Long gateActive) {
		this.gateActive = gateActive;
	}
	
	public Long getGateBuy() {
		return gateBuy;
	}

	public void setGateBuy(Long gateBuy) {
		this.gateBuy = gateBuy;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public BigDecimal getRecervable() {
		return recervable;
	}

	public void setRecervable(BigDecimal recervable) {
		this.recervable = recervable;
	}
	
	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	
	@Length(min=0, max=255, message="客户名称长度必须介于 0 和 255 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=0, max=255, message="根管理账号长度必须介于 0 和 255 之间")
	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}
	
	@Length(min=0, max=255, message="根管理密码长度必须介于 0 和 255 之间")
	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	public Integer getIsBelongCompany() {
		return isBelongCompany;
	}

	public void setIsBelongCompany(Integer isBelongCompany) {
		this.isBelongCompany = isBelongCompany;
	}
	
	@Length(min=0, max=255, message="省份长度必须介于 0 和 255 之间")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Length(min=0, max=255, message="省份编码长度必须介于 0 和 255 之间")
	public String getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}
	
	@Length(min=0, max=255, message="城市长度必须介于 0 和 255 之间")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Length(min=0, max=255, message="城市编码长度必须介于 0 和 255 之间")
	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	
	@Length(min=0, max=255, message="区域长度必须介于 0 和 255 之间")
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	@Length(min=0, max=255, message="区域编码长度必须介于 0 和 255 之间")
	public String getDistrictNo() {
		return districtNo;
	}

	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}
	
	@Length(min=0, max=255, message="详细地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}
	
	@Length(min=0, max=255, message="联系人长度必须介于 0 和 255 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}