/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service.client;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.dao.NodeOfDataDao;
import com.thinkgem.jeesite.modules.bv.dao.client.CustomerNodeOfDataDao;
import com.thinkgem.jeesite.modules.bv.dto.NodeOfDataDto;
import com.thinkgem.jeesite.modules.bv.entity.NodeOfData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 节点测量数据Service
 * @author jinxi
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class CustomerNodeOfDataService extends CrudService<NodeOfDataDao, NodeOfData> {
	
	@Autowired
	private CustomerNodeOfDataDao customerNodeOfDataDao;

	public NodeOfData get(String id) {
		return super.get(id);
	}
	
	public List<NodeOfData> findList(NodeOfData nodeOfData) {
		return super.findList(nodeOfData);
	}
	
	public Page<NodeOfData> findCustomerNodeOfDataByStartTimeToEndTime(Long stime,Long etime,Page<NodeOfData> page, NodeOfData nodeOfData,String usePlaceType,String usePlaceId) {
		NodeOfDataDto nodeOfDataDto = new NodeOfDataDto();
		BeanUtils.copyProperties(nodeOfData, nodeOfDataDto);
		nodeOfDataDto.setPage(page);
		logger.info("nodeofdataservice 开始调用了");
		page.setList(customerNodeOfDataDao.findCustomerNodeOfDataByStartTimeToEndTime(stime,etime,nodeOfDataDto,usePlaceType,usePlaceId));
		return page;
	}
	
	public Page<NodeOfData> findCustomerNodeOfDataGroupByCustomerNodeId(Page<NodeOfData> page, NodeOfData nodeOfData,String usePlaceId) {
		NodeOfDataDto nodeOfDataDto = new NodeOfDataDto();
		BeanUtils.copyProperties(nodeOfData, nodeOfDataDto);
		nodeOfDataDto.setPage(page);
		nodeOfDataDto.setIsMasterDistinct(Boolean.TRUE);
		nodeOfDataDto.setIsNodeDistinct(Boolean.TRUE);
		page.setList(customerNodeOfDataDao.findCustomerNodeOfDataGroupByCustomerNodeId(nodeOfDataDto,usePlaceId));
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