/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.dao.GateEnterDao;
import com.thinkgem.jeesite.modules.bv.entity.GateEnter;

/**
 * 网关入库Service
 * @author jinxi
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class GateEnterService extends CrudService<GateEnterDao, GateEnter> {
	
	public GateEnter get(String id) {
		return super.get(id);
	}
	
	public List<GateEnter> findList(GateEnter gateEnter) {
		return super.findList(gateEnter);
	}
	
	public Page<GateEnter> findPage(Page<GateEnter> page, GateEnter gateEnter) {
		return super.findPage(page, gateEnter);
	}
	
	@Transactional(readOnly = false)
	public void save(GateEnter gateEnter) {
		super.save(gateEnter);
	}
	
	@Transactional(readOnly = false)
	public void delete(GateEnter gateEnter) {
		super.delete(gateEnter);
	}
	
}