/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bv.dto.GateDto;
import com.thinkgem.jeesite.modules.bv.entity.Gate;

/**
 * 网关信息DAO接口
 * @author jinxi
 * @version 2017-12-30
 */
@MyBatisDao
public interface GateDao extends CrudDao<Gate> {
	
	List<Gate> getByGateId(Gate gate);
	
	List<Gate> findBetweenStartAndEnd(GateDto dto);
	
	void updateAllocated(Gate gate);
	List<String> findGateIds();
	
}