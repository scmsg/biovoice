package com.thinkgem.jeesite.modules.bv.dto;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.bv.entity.NodeOfData;

public class NodeOfDataDto extends DataEntity<NodeOfData> {
	
	private static final long serialVersionUID = 1L;
	
	private Long masterId;		// 设备ID(单机版网关ID)
	private Long nodeId;		// 节点ID
	private Byte productType;		// 7~4Bit=（保留 ）3~0Bit=产品类型：1＝温度 2＝温湿度
	private Byte chargingState;		// 充电标识，1为充电，0为未充电
	private Byte battery;		// 设备电池电量（最大4级 0、1、2、3）
	private Byte signalIntensity;		// 信号dbm表（最大16级）
	private Short measureInterval;		// 测量分钟间隔
	private Byte viewDataFlag;		// 查看数据标识1、是查看
	private Byte startRecordFlag;		// 记录开启标识 1、开始记录
	private Byte lightOn;		// 开箱标记,0开关箱,1是开箱
	private Long timeTag;		// 时钟标签，本包第一组数据的测量时间
	private Short temperature;		// 测量温度值
	private Short humidity;		// 测量湿度值

	private Boolean isNodeDistinct;//节点分组
	private Boolean isMasterDistinct;//设备分组
	
	public Long getMasterId() {
		return masterId;
	}
	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public Byte getProductType() {
		return productType;
	}
	public void setProductType(Byte productType) {
		this.productType = productType;
	}
	public Byte getChargingState() {
		return chargingState;
	}
	public void setChargingState(Byte chargingState) {
		this.chargingState = chargingState;
	}
	public Byte getBattery() {
		return battery;
	}
	public void setBattery(Byte battery) {
		this.battery = battery;
	}
	public Byte getSignalIntensity() {
		return signalIntensity;
	}
	public void setSignalIntensity(Byte signalIntensity) {
		this.signalIntensity = signalIntensity;
	}
	public Short getMeasureInterval() {
		return measureInterval;
	}
	public void setMeasureInterval(Short measureInterval) {
		this.measureInterval = measureInterval;
	}
	public Byte getViewDataFlag() {
		return viewDataFlag;
	}
	public void setViewDataFlag(Byte viewDataFlag) {
		this.viewDataFlag = viewDataFlag;
	}
	public Byte getStartRecordFlag() {
		return startRecordFlag;
	}
	public void setStartRecordFlag(Byte startRecordFlag) {
		this.startRecordFlag = startRecordFlag;
	}
	public Byte getLightOn() {
		return lightOn;
	}
	public void setLightOn(Byte lightOn) {
		this.lightOn = lightOn;
	}
	public Long getTimeTag() {
		return timeTag;
	}
	public void setTimeTag(Long timeTag) {
		this.timeTag = timeTag;
	}
	public Short getTemperature() {
		return temperature;
	}
	public void setTemperature(Short temperature) {
		this.temperature = temperature;
	}
	public Short getHumidity() {
		return humidity;
	}
	public void setHumidity(Short humidity) {
		this.humidity = humidity;
	}
	public Boolean getIsNodeDistinct() {
		return isNodeDistinct;
	}
	public void setIsNodeDistinct(Boolean isNodeDistinct) {
		this.isNodeDistinct = isNodeDistinct;
	}
	public Boolean getIsMasterDistinct() {
		return isMasterDistinct;
	}
	public void setIsMasterDistinct(Boolean isMasterDistinct) {
		this.isMasterDistinct = isMasterDistinct;
	}
	
}
