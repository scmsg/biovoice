/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.dao.NodeEnterDao;
import com.thinkgem.jeesite.modules.bv.entity.NodeEnter;

/**
 * 节点入库Service
 * @author jinxi
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class NodeEnterService extends CrudService<NodeEnterDao, NodeEnter> {
	
	public NodeEnter get(String id) {
		return super.get(id);
	}
	
	public List<NodeEnter> findList(NodeEnter nodeEnter) {
		return super.findList(nodeEnter);
	}
	
	public Page<NodeEnter> findPage(Page<NodeEnter> page, NodeEnter nodeEnter) {
		return super.findPage(page, nodeEnter);
	}
	
	@Transactional(readOnly = false)
	public void save(NodeEnter nodeEnter) {
		super.save(nodeEnter);
	}
	
	@Transactional(readOnly = false)
	public void delete(NodeEnter nodeEnter) {
		super.delete(nodeEnter);
	}
	
}