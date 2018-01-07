/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.dao.NodeOfDataDao;
import com.thinkgem.jeesite.modules.bv.dto.NodeOfDataDto;
import com.thinkgem.jeesite.modules.bv.entity.NodeOfData;

/**
 * 节点测量数据Service
 * @author jinxi
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class NodeOfDataService extends CrudService<NodeOfDataDao, NodeOfData> {
	
	@Autowired
	private NodeOfDataDao nodeOfDataDao;

	public NodeOfData get(String id) {
		return super.get(id);
	}
	
	public List<NodeOfData> findList(NodeOfData nodeOfData) {
		return super.findList(nodeOfData);
	}
	
	public Page<NodeOfData> findPage(Page<NodeOfData> page, NodeOfData nodeOfData) {
		return super.findPage(page, nodeOfData);
	}
	
	public Page<NodeOfData> findPageGroubByNodeId(Page<NodeOfData> page, NodeOfData nodeOfData) {
		NodeOfDataDto nodeOfDataDto = new NodeOfDataDto();
		BeanUtils.copyProperties(nodeOfData, nodeOfDataDto);
		nodeOfDataDto.setPage(page);
		nodeOfDataDto.setIsMasterDistinct(Boolean.TRUE);
		nodeOfDataDto.setIsNodeDistinct(Boolean.TRUE);
		page.setList(nodeOfDataDao.findPageGroubByNodeId(nodeOfDataDto));
		return page;
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