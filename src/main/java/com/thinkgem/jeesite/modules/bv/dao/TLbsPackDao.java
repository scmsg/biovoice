/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bv.entity.TLbsPack;

/**
 * 网关在地图上显示DAO接口
 * @author howie
 * @version 2018-01-18
 */
@MyBatisDao
public interface TLbsPackDao extends CrudDao<TLbsPack> {
	
}