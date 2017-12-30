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
import com.thinkgem.jeesite.modules.bv.entity.Gate;
import com.thinkgem.jeesite.modules.bv.service.GateService;

/**
 * 网关信息Controller
 * @author jinxi
 * @version 2017-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/gate")
public class GateController extends BaseController {

	@Autowired
	private GateService gateService;
	
	@ModelAttribute
	public Gate get(@RequestParam(required=false) String id) {
		Gate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gateService.get(id);
		}
		if (entity == null){
			entity = new Gate();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:gate:view")
	@RequestMapping(value = {"list", ""})
	public String list(Gate gate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Gate> page = gateService.findPage(new Page<Gate>(request, response), gate); 
		model.addAttribute("page", page);
		return "modules/bv/gateList";
	}

	@RequiresPermissions("bv:gate:view")
	@RequestMapping(value = "form")
	public String form(Gate gate, Model model) {
		model.addAttribute("gate", gate);
		return "modules/bv/gateForm";
	}

	@RequiresPermissions("bv:gate:edit")
	@RequestMapping(value = "save")
	public String save(Gate gate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gate)){
			return form(gate, model);
		}
		gateService.save(gate);
		addMessage(redirectAttributes, "保存网关信息成功");
		return "redirect:"+Global.getAdminPath()+"/bv/gate/?repage";
	}
	
	@RequiresPermissions("bv:gate:edit")
	@RequestMapping(value = "delete")
	public String delete(Gate gate, RedirectAttributes redirectAttributes) {
		gateService.delete(gate);
		addMessage(redirectAttributes, "删除网关信息成功");
		return "redirect:"+Global.getAdminPath()+"/bv/gate/?repage";
	}

}