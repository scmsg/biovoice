/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.entity.NodeOfData;
import com.thinkgem.jeesite.modules.bv.dao.NodeOfDataDao;

/**
 * 节点测量数据Service
 * @author jinxi
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class NodeOfDataService extends CrudService<NodeOfDataDao, NodeOfData> {

	public NodeOfData get(String id) {
		return super.get(id);
	}
	
	public List<NodeOfData> findList(NodeOfData nodeOfData) {
		return super.findList(nodeOfData);
	}
	
	public Page<NodeOfData> findPage(Page<NodeOfData> page, NodeOfData nodeOfData) {
		return super.findPage(page, nodeOfData);
	}
	
	@Transactional(readOnly = false)
	public void save(NodeOfData nodeOfData) {
		super.save(nodeOfData);
	}
	
	@Transactional(readOnly = false)
	public void delete(NodeOfData nodeOfData) {
		super.delete(nodeOfData);
	}
	
}