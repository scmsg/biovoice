/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 网关在地图上显示Entity
 * @author howie
 * @version 2018-01-18
 */
public class TLbsPack extends DataEntity<TLbsPack> {
	
	private static final long serialVersionUID = 1L;
	private Long masterId;		// 设备ID
	private Long timeTag;		// 设备时钟
	private Long longitude;		// 经度（乘以1000000保留6位小数点后的十六进制值）
	private Long latitude;		// 纬度（乘以1000000保留6位小数点后的十六进制值）
	
	public TLbsPack() {
		super();
	}

	public TLbsPack(String id){
		super(id);
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}
	
	public Long getTimeTag() {
		return timeTag;
	}

	public void setTimeTag(Long timeTag) {
		this.timeTag = timeTag;
	}
	
	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	
	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	
}