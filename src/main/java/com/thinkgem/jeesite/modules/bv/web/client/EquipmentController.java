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
import com.thinkgem.jeesite.modules.bv.entity.client.Equipment;
import com.thinkgem.jeesite.modules.bv.service.client.EquipmentService;

/**
 * 设备管理Controller
 * @author jinxi
 * @version 2018-01-07
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/client/equipment")
public class EquipmentController extends BaseController {

	@Autowired
	private EquipmentService equipmentService;
	
	@ModelAttribute
	public Equipment get(@RequestParam(required=false) String id) {
		Equipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = equipmentService.get(id);
		}
		if (entity == null){
			entity = new Equipment();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:client:equipment:view")
	@RequestMapping(value = {"list", ""})
	public String list(Equipment equipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Equipment> page = equipmentService.findPage(new Page<Equipment>(request, response), equipment); 
		model.addAttribute("page", page);
		if(StringUtils.isNotEmpty(equipment.getDepartmentId())){
			model.addAttribute("departmentId", equipment.getDepartmentId());
		}
		return "modules/bv/client/equipmentList";
	}

	@RequiresPermissions("bv:client:equipment:view")
	@RequestMapping(value = "form")
	public String form(Equipment equipment, Model model) {
		model.addAttribute("equipment", equipment);
		model.addAttribute("departmentId", equipment.getDepartmentId());
		return "modules/bv/client/equipmentForm";
	}

	@RequiresPermissions("bv:client:equipment:edit")
	@RequestMapping(value = "save")
	public String save(Equipment equipment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, equipment)){
			return form(equipment, model);
		}
		if(StringUtils.isEmpty(equipment.getDepartmentId())){
			addMessage(model, "参数异常，请联系管理员");
			return form(equipment, model);
		}
		equipment.setCreateTime(new Date());
		equipment.setUpdateTime(new Date());
		equipmentService.save(equipment);
		addMessage(redirectAttributes, "保存设备管理成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/equipment/?repage";
	}
	
	@RequiresPermissions("bv:client:equipment:edit")
	@RequestMapping(value = "delete")
	public String delete(Equipment equipment, RedirectAttributes redirectAttributes) {
		equipmentService.delete(equipment);
		addMessage(redirectAttributes, "删除设备管理成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/equipment/?repage";
	}

}