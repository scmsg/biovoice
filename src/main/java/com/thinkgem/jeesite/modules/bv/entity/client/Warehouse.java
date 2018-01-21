/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity.client;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 仓库管理Entity
 * @author jinxi
 * @version 2018-01-07
 */
public class Warehouse extends DataEntity<Warehouse> {
	
	private static final long serialVersionUID = 1L;
	private String usePlaceId;			//使用场所ID
	private String warehouseName;		// 仓库名称
	private String managerId;		// 负责人
	private String warehouseType;		// 仓库类型
	private Float highTemperatureAlarm;		// 高温告警值
	private Float highTemperatureWarning;		// 高温预警值
	private Float lowTemperatureWarning;		// 低温预警值
	private Float lowTemperatureAlarm;		// 低温告警值
	private Float highHumidityAlarm;		// 高湿度报警
	private Float highHumidityWarning;		// 高湿度预警
	private Float lowHumidityWarning;		// 低湿度预警
	private Float lowHumidityAlarm;		// 低湿度告警
	private String warningPhone1;		// 预警电话1
	private String warningPhone2;		// 预警电话2
	private String warningPhone3;		// 预警电话3
	private String alarmPhone1;		// 告警电话1
	private String alarmPhone2;		// 告警电话2
	private String alarmPhone3;		// 告警电话3
	private Integer measuerPeriod;		// 测量周期
	private Integer warehouseArea;		// 仓库储存面积
	private Integer outletNum;		// 出风口个数
	private Date buildTime;		// 建成时间
	private String constructionUnit;		// 施工单位
	private String warehousePlan;		// 仓库平面图
	private Integer warehouseHight;		// 仓库层高(米)
	private Integer placement;		// 货位个数
	private String specification;		// 货位规格
	private String verificationUnit;		// 验证单位
	private Date verificationTime;		// 验证时间
	private String verificationReport;		// 验证报告
	private String departmentId;		// 部门ID
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public Warehouse() {
		super();
	}

	public Warehouse(String id){
		super(id);
	}
	
	public String getUsePlaceId() {
		return usePlaceId;
	}

	public void setUsePlaceId(String usePlaceId) {
		this.usePlaceId = usePlaceId;
	}

	@Length(min=0, max=255, message="仓库名称长度必须介于 0 和 255 之间")
	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	@Length(min=0, max=64, message="负责人长度必须介于 0 和 64 之间")
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	
	@Length(min=0, max=255, message="仓库类型长度必须介于 0 和 255 之间")
	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	
	public Float getHighTemperatureAlarm() {
		return highTemperatureAlarm;
	}

	public void setHighTemperatureAlarm(Float highTemperatureAlarm) {
		this.highTemperatureAlarm = highTemperatureAlarm;
	}
	
	public Float getHighTemperatureWarning() {
		return highTemperatureWarning;
	}

	public void setHighTemperatureWarning(Float highTemperatureWarning) {
		this.highTemperatureWarning = highTemperatureWarning;
	}
	
	public Float getLowTemperatureWarning() {
		return lowTemperatureWarning;
	}

	public void setLowTemperatureWarning(Float lowTemperatureWarning) {
		this.lowTemperatureWarning = lowTemperatureWarning;
	}
	
	public Float getLowTemperatureAlarm() {
		return lowTemperatureAlarm;
	}

	public void setLowTemperatureAlarm(Float lowTemperatureAlarm) {
		this.lowTemperatureAlarm = lowTemperatureAlarm;
	}
	
	public Float getHighHumidityAlarm() {
		return highHumidityAlarm;
	}

	public void setHighHumidityAlarm(Float highHumidityAlarm) {
		this.highHumidityAlarm = highHumidityAlarm;
	}
	
	public Float getHighHumidityWarning() {
		return highHumidityWarning;
	}

	public void setHighHumidityWarning(Float highHumidityWarning) {
		this.highHumidityWarning = highHumidityWarning;
	}
	
	public Float getLowHumidityWarning() {
		return lowHumidityWarning;
	}

	public void setLowHumidityWarning(Float lowHumidityWarning) {
		this.lowHumidityWarning = lowHumidityWarning;
	}
	
	public Float getLowHumidityAlarm() {
		return lowHumidityAlarm;
	}

	public void setLowHumidityAlarm(Float lowHumidityAlarm) {
		this.lowHumidityAlarm = lowHumidityAlarm;
	}
	
	@Length(min=0, max=255, message="告警电话1长度必须介于 0 和 255 之间")
	public String getWarningPhone1() {
		return warningPhone1;
	}

	public void setWarningPhone1(String warningPhone1) {
		this.warningPhone1 = warningPhone1;
	}
	
	@Length(min=0, max=255, message="告警电话2长度必须介于 0 和 255 之间")
	public String getWarningPhone2() {
		return warningPhone2;
	}

	public void setWarningPhone2(String warningPhone2) {
		this.warningPhone2 = warningPhone2;
	}
	
	@Length(min=0, max=255, message="预警电话3长度必须介于 0 和 255 之间")
	public String getWarningPhone3() {
		return warningPhone3;
	}

	public void setWarningPhone3(String warningPhone3) {
		this.warningPhone3 = warningPhone3;
	}
	
	@Length(min=0, max=255, message="告警电话1长度必须介于 0 和 255 之间")
	public String getAlarmPhone1() {
		return alarmPhone1;
	}

	public void setAlarmPhone1(String alarmPhone1) {
		this.alarmPhone1 = alarmPhone1;
	}
	
	@Length(min=0, max=255, message="告警电话2长度必须介于 0 和 255 之间")
	public String getAlarmPhone2() {
		return alarmPhone2;
	}

	public void setAlarmPhone2(String alarmPhone2) {
		this.alarmPhone2 = alarmPhone2;
	}
	
	@Length(min=0, max=255, message="告警电话3长度必须介于 0 和 255 之间")
	public String getAlarmPhone3() {
		return alarmPhone3;
	}

	public void setAlarmPhone3(String alarmPhone3) {
		this.alarmPhone3 = alarmPhone3;
	}
	
	public Integer getMeasuerPeriod() {
		return measuerPeriod;
	}

	public void setMeasuerPeriod(Integer measuerPeriod) {
		this.measuerPeriod = measuerPeriod;
	}
	
	public Integer getWarehouseArea() {
		return warehouseArea;
	}

	public void setWarehouseArea(Integer warehouseArea) {
		this.warehouseArea = warehouseArea;
	}
	
	public Integer getOutletNum() {
		return outletNum;
	}

	public void setOutletNum(Integer outletNum) {
		this.outletNum = outletNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}
	
	@Length(min=0, max=255, message="施工单位长度必须介于 0 和 255 之间")
	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}
	
	@Length(min=0, max=255, message="仓库平面图长度必须介于 0 和 255 之间")
	public String getWarehousePlan() {
		return warehousePlan;
	}

	public void setWarehousePlan(String warehousePlan) {
		this.warehousePlan = warehousePlan;
	}
	
	public Integer getWarehouseHight() {
		return warehouseHight;
	}

	public void setWarehouseHight(Integer warehouseHight) {
		this.warehouseHight = warehouseHight;
	}
	
	public Integer getPlacement() {
		return placement;
	}

	public void setPlacement(Integer placement) {
		this.placement = placement;
	}
	
	@Length(min=0, max=255, message="货位规格长度必须介于 0 和 255 之间")
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	@Length(min=0, max=255, message="验证单位长度必须介于 0 和 255 之间")
	public String getVerificationUnit() {
		return verificationUnit;
	}

	public void setVerificationUnit(String verificationUnit) {
		this.verificationUnit = verificationUnit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVerificationTime() {
		return verificationTime;
	}

	public void setVerificationTime(Date verificationTime) {
		this.verificationTime = verificationTime;
	}
	
	@Length(min=0, max=255, message="验证报告长度必须介于 0 和 255 之间")
	public String getVerificationReport() {
		return verificationReport;
	}

	public void setVerificationReport(String verificationReport) {
		this.verificationReport = verificationReport;
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