/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.entity.client.Equipment;
import com.thinkgem.jeesite.modules.bv.dao.client.EquipmentDao;

/**
 * 设备管理Service
 * @author jinxi
 * @version 2018-01-07
 */
@Service
@Transactional(readOnly = true)
public class EquipmentService extends CrudService<EquipmentDao, Equipment> {

	public Equipment get(String id) {
		return super.get(id);
	}
	
	public List<Equipment> findList(Equipment equipment) {
		return super.findList(equipment);
	}
	
	public Page<Equipment> findPage(Page<Equipment> page, Equipment equipment) {
		return super.findPage(page, equipment);
	}
	
	@Transactional(readOnly = false)
	public void save(Equipment equipment) {
		super.save(equipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(Equipment equipment) {
		super.delete(equipment);
	}
	
}