/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bv.dto.NodeDto;
import com.thinkgem.jeesite.modules.bv.entity.Node;

/**
 * 节点信息DAO接口
 * @author jinxi
 * @version 2017-12-30
 */
@MyBatisDao
public interface NodeDao extends CrudDao<Node> {
	
	List<Node> getByNodeId(Node node);
	
	List<Node> findBetweenStartAndEnd(NodeDto dto);
	
	void updateAllocated(Node node);
}