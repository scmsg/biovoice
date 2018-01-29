/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.entity.TLbsPack;
import com.thinkgem.jeesite.modules.bv.dao.TLbsPackDao;

/**
 * 网关在地图上显示Service
 * @author howie
 * @version 2018-01-18
 */
@Service
@Transactional(readOnly = true)
public class TLbsPackService extends CrudService<TLbsPackDao, TLbsPack> {

	public TLbsPack get(String id) {
		return super.get(id);
	}
	
	public List<TLbsPack> findList(TLbsPack tLbsPack) {
		return super.findList(tLbsPack);
	}
	
	public Page<TLbsPack> findPage(Page<TLbsPack> page, TLbsPack tLbsPack) {
		return super.findPage(page, tLbsPack);
	}
	
	@Transactional(readOnly = false)
	public void save(TLbsPack tLbsPack) {
		super.save(tLbsPack);
	}
	
	@Transactional(readOnly = false)
	public void delete(TLbsPack tLbsPack) {
		super.delete(tLbsPack);
	}
	
}