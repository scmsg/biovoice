/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 节点信息Entity
 * @author jinxi
 * @version 2017-12-30
 */
public class Node extends DataEntity<Node> {
	
	private static final long serialVersionUID = 1L;
	private Long nodeId;		// 节点ID
	private String firmwareVersion;		// 固件版本
	private Date enterTime;		// 入库时间
	private Date lastQuasiTime;		// 上次较准时间
	private String quasiReport;		// 上传较准报告
	private MultipartFile quasiReportFile;		// 上传较准报告
	private String remark;		// 备注
	private String placePosition;		// 摆放位置
	private String whichVessel;		// 所属容器
	private Integer boundStatus;		// 绑定状态(0:未绑定； 1:已绑定)
	private Date boundTime;		// 绑定时间
	private Integer isAllocated;		// 是否已分配(0:未分配； 1:已分配)
	//private String whichCustomer;		// 所属客户
	private Long customerId;		// 所属客户
	
	public Node() {
		super();
	}

	public Node(String id){
		super(id);
	}
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastQuasiTime() {
		return lastQuasiTime;
	}

	public void setLastQuasiTime(Date lastQuasiTime) {
		this.lastQuasiTime = lastQuasiTime;
	}

	public MultipartFile getQuasiReportFile() {
		return quasiReportFile;
	}

	public void setQuasiReportFile(MultipartFile quasiReportFile) {
		this.quasiReportFile = quasiReportFile;
	}

	@Length(min=0, max=255, message="上传较准报告长度必须介于 0 和 255 之间")
	public String getQuasiReport() {
		return quasiReport;
	}

	public void setQuasiReport(String quasiReport) {
		this.quasiReport = quasiReport;
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
	
	@Length(min=0, max=255, message="所属容器长度必须介于 0 和 255 之间")
	public String getWhichVessel() {
		return whichVessel;
	}

	public void setWhichVessel(String whichVessel) {
		this.whichVessel = whichVessel;
	}
	
	public Integer getBoundStatus() {
		return boundStatus;
	}

	public void setBoundStatus(Integer boundStatus) {
		this.boundStatus = boundStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBoundTime() {
		return boundTime;
	}

	public void setBoundTime(Date boundTime) {
		this.boundTime = boundTime;
	}
	
	public Integer getIsAllocated() {
		return isAllocated;
	}

	public void setIsAllocated(Integer isAllocated) {
		this.isAllocated = isAllocated;
	}

	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}