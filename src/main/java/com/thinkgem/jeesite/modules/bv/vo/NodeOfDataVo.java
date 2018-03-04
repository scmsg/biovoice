package com.thinkgem.jeesite.modules.bv.vo;

public class NodeOfDataVo {

    private Long timeTag;		// 时钟标签，本包第一组数据的测量时间
    private Short temperature;		// 测量温度值

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
}
