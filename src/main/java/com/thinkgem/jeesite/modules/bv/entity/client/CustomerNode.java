/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity.client;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 客户节点Entity
 * @author jinxi
 * @version 2018-01-07
 */
public class CustomerNode extends DataEntity<CustomerNode> {
	
	private static final long serialVersionUID = 1L;
	private String nodeName;		// 节点名称(客户自定义)
	private Long nodeId;		// 节点ID
	private String customerNodeIds;//节点组数据
	private String placementPosition;		// 摆放位置
	private Date verificationTime;		// 上次较准时间
	private String verificationReport;		// 较准报告
	private String usePlaceType;		// 使用场所类型
	private String usePlaceId;		// 使用场所ID
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public CustomerNode() {
		super();
	}

	public CustomerNode(String id){
		super(id);
	}

	@Length(min=0, max=255, message="节点名称(客户自定义)长度必须介于 0 和 255 之间")
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	@Length(min=0, max=255, message="摆放位置长度必须介于 0 和 255 之间")
	public String getPlacementPosition() {
		return placementPosition;
	}

	public void setPlacementPosition(String placementPosition) {
		this.placementPosition = placementPosition;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVerificationTime() {
		return verificationTime;
	}

	public void setVerificationTime(Date verificationTime) {
		this.verificationTime = verificationTime;
	}
	
	@Length(min=0, max=255, message="较准报告长度必须介于 0 和 255 之间")
	public String getVerificationReport() {
		return verificationReport;
	}

	public void setVerificationReport(String verificationReport) {
		this.verificationReport = verificationReport;
	}
	
	@Length(min=0, max=255, message="使用场所类型长度必须介于 0 和 255 之间")
	public String getUsePlaceType() {
		return usePlaceType;
	}

	public void setUsePlaceType(String usePlaceType) {
		this.usePlaceType = usePlaceType;
	}
	
	@Length(min=0, max=64, message="使用场所ID长度必须介于 0 和 64 之间")
	public String getUsePlaceId() {
		return usePlaceId;
	}

	public void setUsePlaceId(String usePlaceId) {
		this.usePlaceId = usePlaceId;
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

	public String getCustomerNodeIds() {
		return customerNodeIds;
	}
	public void setCustomerNodeIds(String customerNodeIds) {
		this.customerNodeIds = customerNodeIds;
	}
}