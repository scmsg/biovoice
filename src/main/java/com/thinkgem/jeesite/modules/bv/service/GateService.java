/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.dao.GateDao;
import com.thinkgem.jeesite.modules.bv.dto.GateDto;
import com.thinkgem.jeesite.modules.bv.entity.Gate;

/**
 * 网关信息Service
 * @author jinxi
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class GateService extends CrudService<GateDao, Gate> {
	
	@Autowired
	private GateDao gateDao;

	public Gate get(String id) {
		return super.get(id);
	}
	
	public Gate getByGateId(Gate gate){
		List<Gate> gates = gateDao.getByGateId(gate);
		if(null == gates || gates.size() == 0){
			return null;
		}
		return gates.get(0);
	}
	
	public List<Gate> findList(Gate gate) {
		return super.findList(gate);
	}
	
	public List<Gate> findBetweenStartAndEnd(GateDto dto) {
		return gateDao.findBetweenStartAndEnd(dto);
	}
	
	public Page<Gate> findPage(Page<Gate> page, Gate gate) {
		return super.findPage(page, gate);
	}
	
	@Transactional(readOnly = false)
	public void save(Gate gate) {
		super.save(gate);
	}
	
	@Transactional(readOnly = false)
	public void updateAllocated(Gate gate){
		gateDao.updateAllocated(gate);
	}
	
	@Transactional(readOnly = false)
	public void delete(Gate gate) {
		super.delete(gate);
	}
	
}