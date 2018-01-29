/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web;

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
import com.thinkgem.jeesite.modules.bv.entity.TLbsPack;
import com.thinkgem.jeesite.modules.bv.service.TLbsPackService;

/**
 * 网关在地图上显示Controller
 * @author howie
 * @version 2018-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/tLbsPack")
public class TLbsPackController extends BaseController {

	@Autowired
	private TLbsPackService tLbsPackService;
	
	@ModelAttribute
	public TLbsPack get(@RequestParam(required=false) String id) {
		TLbsPack entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tLbsPackService.get(id);
		}
		if (entity == null){
			entity = new TLbsPack();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:tLbsPack:view")
	@RequestMapping(value = {"list", ""})
	public String list(TLbsPack tLbsPack, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TLbsPack> page = tLbsPackService.findPage(new Page<TLbsPack>(request, response), tLbsPack); 
		model.addAttribute("page", page);
		//return "modules/bv/tLbsPackList";
		return "modules/bv/bvMap";
	}

	@RequiresPermissions("bv:tLbsPack:view")
	@RequestMapping(value = "form")
	public String form(TLbsPack tLbsPack, Model model) {
		model.addAttribute("tLbsPack", tLbsPack);
		return "modules/bv/tLbsPackForm";
	}

	@RequiresPermissions("bv:tLbsPack:edit")
	@RequestMapping(value = "save")
	public String save(TLbsPack tLbsPack, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tLbsPack)){
			return form(tLbsPack, model);
		}
		tLbsPackService.save(tLbsPack);
		addMessage(redirectAttributes, "保存网关地图生成成功成功");
		return "redirect:"+Global.getAdminPath()+"/bv/tLbsPack/?repage";
	}
	
	@RequiresPermissions("bv:tLbsPack:edit")
	@RequestMapping(value = "delete")
	public String delete(TLbsPack tLbsPack, RedirectAttributes redirectAttributes) {
		tLbsPackService.delete(tLbsPack);
		addMessage(redirectAttributes, "删除网关地图生成成功成功");
		return "redirect:"+Global.getAdminPath()+"/bv/tLbsPack/?repage";
	}

}