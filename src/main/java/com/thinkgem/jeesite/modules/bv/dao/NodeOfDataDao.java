/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bv.entity.NodeOfData;

/**
 * 节点测量数据DAO接口
 * @author jinxi
 * @version 2017-12-30
 */
@MyBatisDao
public interface NodeOfDataDao extends CrudDao<NodeOfData> {
	
}