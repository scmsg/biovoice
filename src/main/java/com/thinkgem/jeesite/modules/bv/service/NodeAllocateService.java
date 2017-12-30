/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.entity.NodeAllocate;
import com.thinkgem.jeesite.modules.bv.dao.NodeAllocateDao;

/**
 * 节点分配Service
 * @author jinxi
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class NodeAllocateService extends CrudService<NodeAllocateDao, NodeAllocate> {

	public NodeAllocate get(String id) {
		return super.get(id);
	}
	
	public List<NodeAllocate> findList(NodeAllocate nodeAllocate) {
		return super.findList(nodeAllocate);
	}
	
	public Page<NodeAllocate> findPage(Page<NodeAllocate> page, NodeAllocate nodeAllocate) {
		return super.findPage(page, nodeAllocate);
	}
	
	@Transactional(readOnly = false)
	public void save(NodeAllocate nodeAllocate) {
		super.save(nodeAllocate);
	}
	
	@Transactional(readOnly = false)
	public void delete(NodeAllocate nodeAllocate) {
		super.delete(nodeAllocate);
	}
	
}