/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.service.client;

import java.util.List;

import com.thinkgem.jeesite.modules.bv.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bv.entity.client.UserMenu;
import com.thinkgem.jeesite.modules.bv.dao.client.UserMenuDao;

/**
 * 客户端的用户菜单权限Service
 * @author jinxi
 * @version 2018-02-14
 */
@Service
@Transactional(readOnly = true)
public class UserMenuService extends CrudService<UserMenuDao, UserMenu> {

	@Autowired
	private UserMenuDao userMenuDao;

	public UserMenu get(String id) {
		return super.get(id);
	}
	
	public List<UserMenu> findList(UserMenu userMenu) {
		return super.findList(userMenu);
	}
	
	public Page<UserMenu> findPage(Page<UserMenu> page, UserMenu userMenu) {
		return super.findPage(page, userMenu);
	}
	
	@Transactional(readOnly = false)
	public void save(UserMenu userMenu) {
		super.save(userMenu);
	}
	
	@Transactional(readOnly = false)
	public void delete(UserMenu userMenu) {
		super.delete(userMenu);
	}

	/**
	 * 客户端的菜单权限
	 * @param customer
	 */
	@Transactional(readOnly = false)
	public void saveUserMenu(Customer customer) {
		List<UserMenu> userMenuList = customer.getUserMenuList();
		if(null == userMenuList || userMenuList.size() == 0){
			//清空菜单权限
			userMenuDao.deleteUserMenu(customer);
		}else{
			//1.先清空
			userMenuDao.deleteUserMenu(customer);

			//2.再添加
			userMenuDao.insertUserMenu(customer);
		}
	}
	
}