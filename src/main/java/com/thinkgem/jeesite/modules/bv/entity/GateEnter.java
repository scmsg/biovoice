/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 网关入库Entity
 * @author jinxi
 * @version 2017-12-30
 */
public class GateEnter extends DataEntity<GateEnter> {
	
	private static final long serialVersionUID = 1L;
	private Long startId;		// 起始ID
	private Integer enterNumber;		// 数量
	private Date enterTime;		// 入库时间
	private Long endId;		// 结束ID
	
	public GateEnter() {
		super();
	}

	public GateEnter(String id){
		super(id);
	}

	public Long getStartId() {
		return startId;
	}

	public void setStartId(Long startId) {
		this.startId = startId;
	}
	
	public Integer getEnterNumber() {
		return enterNumber;
	}

	public void setEnterNumber(Integer enterNumber) {
		this.enterNumber = enterNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	
	public Long getEndId() {
		return endId;
	}

	public void setEndId(Long endId) {
		this.endId = endId;
	}
	
}