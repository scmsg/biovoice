/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.entity.client.Warehouse;
import com.thinkgem.jeesite.modules.bv.dao.client.WarehouseDao;

/**
 * 仓库管理Service
 * @author jinxi
 * @version 2018-01-07
 */
@Service
@Transactional(readOnly = true)
public class WarehouseService extends CrudService<WarehouseDao, Warehouse> {
	@Autowired
	private WarehouseDao warehouseDao;
	public Warehouse get(String id) {
		return super.get(id);
	}
	
	public List<Warehouse> findList(Warehouse warehouse) {
		return super.findList(warehouse);
	}
	
	public Page<Warehouse> findPage(Page<Warehouse> page, Warehouse warehouse) {
		return super.findPage(page, warehouse);
	}
	
	@Transactional(readOnly = false)
	public void save(Warehouse warehouse) {
		super.save(warehouse);
	}
	
	@Transactional(readOnly = false)
	public void delete(Warehouse warehouse) {
		super.delete(warehouse);
	}
	@Transactional(readOnly = false)
	public void deleteUsePlaceAndWarhouse(Warehouse warehouse) {
		warehouseDao.deleteUsePlaceAndWarhouse(warehouse);
	}
	
}