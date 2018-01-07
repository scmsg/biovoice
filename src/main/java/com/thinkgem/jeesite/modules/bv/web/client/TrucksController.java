/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web.client;

import java.util.Date;

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
import com.thinkgem.jeesite.modules.bv.entity.client.Trucks;
import com.thinkgem.jeesite.modules.bv.service.client.TrucksService;

/**
 * 车辆管理Controller
 * @author jinxi
 * @version 2018-01-07
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/client/trucks")
public class TrucksController extends BaseController {

	@Autowired
	private TrucksService trucksService;
	
	@ModelAttribute
	public Trucks get(@RequestParam(required=false) String id) {
		Trucks entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = trucksService.get(id);
		}
		if (entity == null){
			entity = new Trucks();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:client:trucks:view")
	@RequestMapping(value = {"list", ""})
	public String list(Trucks trucks, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Trucks> page = trucksService.findPage(new Page<Trucks>(request, response), trucks); 
		model.addAttribute("page", page);
		if(StringUtils.isNotEmpty(trucks.getDepartmentId())){
			model.addAttribute("departmentId", trucks.getDepartmentId());
		}
		return "modules/bv/client/trucksList";
	}

	@RequiresPermissions("bv:client:trucks:view")
	@RequestMapping(value = "form")
	public String form(Trucks trucks, Model model) {
		model.addAttribute("trucks", trucks);
		model.addAttribute("departmentId", trucks.getDepartmentId());
		return "modules/bv/client/trucksForm";
	}

	@RequiresPermissions("bv:client:trucks:edit")
	@RequestMapping(value = "save")
	public String save(Trucks trucks, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, trucks)){
			return form(trucks, model);
		}
		if(StringUtils.isEmpty(trucks.getDepartmentId())){
			addMessage(model, "参数异常，请联系管理员");
			return form(trucks, model);
		}
		trucks.setCreateTime(new Date());
		trucks.setUpdateTime(new Date());
		trucksService.save(trucks);
		addMessage(redirectAttributes, "保存车辆管理成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/trucks/?repage";
	}
	
	@RequiresPermissions("bv:client:trucks:edit")
	@RequestMapping(value = "delete")
	public String delete(Trucks trucks, RedirectAttributes redirectAttributes) {
		trucksService.delete(trucks);
		addMessage(redirectAttributes, "删除车辆管理成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/trucks/?repage";
	}

}