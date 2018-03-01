/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.entity.client;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 客户端的用户菜单权限Entity
 * @author jinxi
 * @version 2018-02-14
 */
public class UserMenu extends DataEntity<UserMenu> {
	
	private static final long serialVersionUID = 1L;
	private Long userId;		// 用户ID
	private String menuId;		// 虚拟菜单ID
	private String menuPId;		// 虚拟菜单的上级ID
	private String name;		// 名字/菜单名字
	private String open;		// 是否打开
	private String file;		// 径路
	private String remark;		// 备注
	
	public UserMenu() {
		super();
	}

	public UserMenu(String id){
		super(id);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	public String getMenuPId() {
		return menuPId;
	}

	public void setMenuPId(String menuPId) {
		this.menuPId = menuPId;
	}
	
	@Length(min=0, max=255, message="名字/菜单名字长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="是否打开长度必须介于 0 和 32 之间")
	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}
	
	@Length(min=0, max=255, message="径路长度必须介于 0 和 255 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}