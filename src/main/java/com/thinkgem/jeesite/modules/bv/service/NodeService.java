/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.dao.NodeDao;
import com.thinkgem.jeesite.modules.bv.dto.NodeDto;
import com.thinkgem.jeesite.modules.bv.entity.Node;

/**
 * 节点信息Service
 * @author jinxi
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class NodeService extends CrudService<NodeDao, Node> {
	
	@Autowired
	private NodeDao nodeDao;

	public Node get(String id) {
		return super.get(id);
	}
	
	public Node getByNodeId(Node node){
		List<Node> nodes = nodeDao.getByNodeId(node);
		if(null == nodes || nodes.size() == 0){
			return null;
		}
		return nodes.get(0);
	}
	
	public List<Node> findList(Node node) {
		return super.findList(node);
	}
	
	public List<Node> findBetweenStartAndEnd(NodeDto dto) {
		return nodeDao.findBetweenStartAndEnd(dto);
	}
	
	public Page<Node> findPage(Page<Node> page, Node node) {
		return super.findPage(page, node);
	}
	
	@Transactional(readOnly = false)
	public void save(Node node) {
		super.save(node);
	}
	
	@Transactional(readOnly = false)
	public void updateAllocated(Node node){
		nodeDao.updateAllocated(node);
	}
	
	@Transactional(readOnly = false)
	public void delete(Node node) {
		super.delete(node);
	}
	
}