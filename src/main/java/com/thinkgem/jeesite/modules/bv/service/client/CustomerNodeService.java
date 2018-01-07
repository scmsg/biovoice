/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.entity.client.CustomerNode;
import com.thinkgem.jeesite.modules.bv.dao.client.CustomerNodeDao;

/**
 * 客户节点Service
 * @author jinxi
 * @version 2018-01-07
 */
@Service
@Transactional(readOnly = true)
public class CustomerNodeService extends CrudService<CustomerNodeDao, CustomerNode> {

	public CustomerNode get(String id) {
		return super.get(id);
	}
	
	public List<CustomerNode> findList(CustomerNode customerNode) {
		return super.findList(customerNode);
	}
	
	public Page<CustomerNode> findPage(Page<CustomerNode> page, CustomerNode customerNode) {
		return super.findPage(page, customerNode);
	}
	
	@Transactional(readOnly = false)
	public void save(CustomerNode customerNode) {
		super.save(customerNode);
	}
	
	@Transactional(readOnly = false)
	public void delete(CustomerNode customerNode) {
		super.delete(customerNode);
	}
	
}