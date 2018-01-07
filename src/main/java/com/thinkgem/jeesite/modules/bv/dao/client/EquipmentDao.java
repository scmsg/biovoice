/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.dao.client;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bv.entity.client.Equipment;

/**
 * 设备管理DAO接口
 * @author jinxi
 * @version 2018-01-07
 */
@MyBatisDao
public interface EquipmentDao extends CrudDao<Equipment> {
	
}