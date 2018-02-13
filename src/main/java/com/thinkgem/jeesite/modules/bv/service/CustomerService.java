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
import com.thinkgem.jeesite.modules.bv.entity.Customer;
import com.thinkgem.jeesite.modules.bv.dao.CustomerDao;

/**
 * 客户信息Service
 * @author jinxi
 * @version 2017-12-30
 */
@Service
@Transactional(readOnly = true)
public class CustomerService extends CrudService<CustomerDao, Customer> {
	@Autowired
	private CustomerDao customerDao;
	
	public Customer get(String id) {
		return super.get(id);
	}
	
	public Customer getByAdminAccount(Customer customer) {
		return customerDao.getByAdminAccount(customer);
	}
	
	public List<Customer> findList(Customer customer) {
		return super.findList(customer);
	}
	
	public Page<Customer> findPage(Page<Customer> page, Customer customer) {
		return super.findPage(page, customer);
	}
	
	@Transactional(readOnly = false)
	public void save(Customer customer) {
		super.save(customer);
	}
	
	@Transactional(readOnly = false)
	public void delete(Customer customer) {
		super.delete(customer);
	}

	@Transactional(readOnly = false)
	public List<Customer> findCompanyList() {
		return  customerDao.findCompanyList();
	}
	@Transactional(readOnly = false)
	public String checkCompanyName(String name) {
		return customerDao.checkCompanyName(name);
/*		if(str != "" && str != null) {
			return true;
		}else {
			return  false;
		}*/
	}
}