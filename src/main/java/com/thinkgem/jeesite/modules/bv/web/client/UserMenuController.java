/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bv.entity.client.UserMenu;
import com.thinkgem.jeesite.modules.bv.service.client.UserMenuService;

/**
 * 客户端的用户菜单权限Controller
 * @author jinxi
 * @version 2018-02-14
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/client/userMenu")
public class UserMenuController extends BaseController {

	@Autowired
	private UserMenuService userMenuService;
	
	@ModelAttribute
	public UserMenu get(@RequestParam(required=false) String id) {
		UserMenu entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userMenuService.get(id);
		}
		if (entity == null){
			entity = new UserMenu();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:client:userMenu:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserMenu userMenu, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserMenu> page = userMenuService.findPage(new Page<UserMenu>(request, response), userMenu); 
		model.addAttribute("page", page);
		return "modules/bv/client/userMenuList";
	}

	@RequiresPermissions("bv:client:userMenu:view")
	@RequestMapping(value = "form")
	public String form(UserMenu userMenu, Model model) {
		model.addAttribute("userMenu", userMenu);
		return "modules/bv/client/userMenuForm";
	}

	@RequiresPermissions("bv:client:userMenu:edit")
	@RequestMapping(value = "save")
	public String save(UserMenu userMenu, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userMenu)){
			return form(userMenu, model);
		}
		userMenuService.save(userMenu);
		addMessage(redirectAttributes, "保存客户端的用户菜单权限成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/userMenu/?repage";
	}
	
	@RequiresPermissions("bv:client:userMenu:edit")
	@RequestMapping(value = "delete")
	public String delete(UserMenu userMenu, RedirectAttributes redirectAttributes) {
		userMenuService.delete(userMenu);
		addMessage(redirectAttributes, "删除客户端的用户菜单权限成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/userMenu/?repage";
	}

}