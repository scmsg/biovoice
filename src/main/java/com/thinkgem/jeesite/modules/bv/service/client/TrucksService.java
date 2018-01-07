/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.entity.client.Trucks;
import com.thinkgem.jeesite.modules.bv.dao.client.TrucksDao;

/**
 * 车辆管理Service
 * @author jinxi
 * @version 2018-01-07
 */
@Service
@Transactional(readOnly = true)
public class TrucksService extends CrudService<TrucksDao, Trucks> {

	public Trucks get(String id) {
		return super.get(id);
	}
	
	public List<Trucks> findList(Trucks trucks) {
		return super.findList(trucks);
	}
	
	public Page<Trucks> findPage(Page<Trucks> page, Trucks trucks) {
		return super.findPage(page, trucks);
	}
	
	@Transactional(readOnly = false)
	public void save(Trucks trucks) {
		super.save(trucks);
	}
	
	@Transactional(readOnly = false)
	public void delete(Trucks trucks) {
		super.delete(trucks);
	}
	
}