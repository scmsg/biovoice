/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.entity.client.UsePlace;
import com.thinkgem.jeesite.modules.bv.dao.client.UsePlaceDao;

/**
 * 使用场所Service
 * @author jinxi
 * @version 2018-01-21
 */
@Service
@Transactional(readOnly = true)
public class UsePlaceService extends CrudService<UsePlaceDao, UsePlace> {

	public UsePlace get(String id) {
		return super.get(id);
	}
	
	public List<UsePlace> findList(UsePlace usePlace) {
		return super.findList(usePlace);
	}
	
	public Page<UsePlace> findPage(Page<UsePlace> page, UsePlace usePlace) {
		return super.findPage(page, usePlace);
	}
	
	@Transactional(readOnly = false)
	public void save(UsePlace usePlace) {
		super.save(usePlace);
	}
	
	@Transactional(readOnly = false)
	public void delete(UsePlace usePlace) {
		super.delete(usePlace);
	}
	
}