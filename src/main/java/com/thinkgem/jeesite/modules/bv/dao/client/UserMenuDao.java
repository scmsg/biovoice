/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.dao.client;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bv.entity.Customer;
import com.thinkgem.jeesite.modules.bv.entity.client.UserMenu;

/**
 * 客户端的用户菜单权限DAO接口
 * @author jinxi
 * @version 2018-02-14
 */
@MyBatisDao
public interface UserMenuDao extends CrudDao<UserMenu> {

    int deleteUserMenu(Customer customer);

    int insertUserMenu(Customer customer);
	
}