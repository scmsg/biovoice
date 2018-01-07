/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.entity.client.Depatement;
import com.thinkgem.jeesite.modules.bv.dao.client.DepatementDao;

/**
 * 部门Service
 * @author jinxi
 * @version 2018-01-07
 */
@Service
@Transactional(readOnly = true)
public class DepatementService extends CrudService<DepatementDao, Depatement> {

	public Depatement get(String id) {
		return super.get(id);
	}
	
	public List<Depatement> findList(Depatement depatement) {
		return super.findList(depatement);
	}
	
	public Page<Depatement> findPage(Page<Depatement> page, Depatement depatement) {
		return super.findPage(page, depatement);
	}
	
	@Transactional(readOnly = false)
	public void save(Depatement depatement) {
		super.save(depatement);
	}
	
	@Transactional(readOnly = false)
	public void delete(Depatement depatement) {
		super.delete(depatement);
	}
	
}