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
import com.thinkgem.jeesite.common.utils.JacksonBundle;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bv.entity.client.Warehouse;
import com.thinkgem.jeesite.modules.bv.service.client.WarehouseService;

/**
 * 仓库管理Controller
 * @author jinxi
 * @version 2018-01-07
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/client/warehouse")
public class WarehouseController extends BaseController {

	@Autowired
	private WarehouseService warehouseService;
	
	@ModelAttribute
	public Warehouse get(@RequestParam(required=false) String id) {
		Warehouse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = warehouseService.get(id);
		}
		if (entity == null){
			entity = new Warehouse();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:client:warehouse:view")
	@RequestMapping(value = {"list", ""})
	public String list(Warehouse warehouse, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("warehouse: "+JacksonBundle.nonNullMapper().toJson(warehouse));
		Page<Warehouse> page = warehouseService.findPage(new Page<Warehouse>(request, response), warehouse); 
		model.addAttribute("page", page);
		if(StringUtils.isNotEmpty(warehouse.getDepartmentId())){
			model.addAttribute("departmentId", warehouse.getDepartmentId());
		}
		return "modules/bv/client/warehouseList";
	}

	@RequiresPermissions("bv:client:warehouse:view")
	@RequestMapping(value = "form")
	public String form(Warehouse warehouse, Model model) {
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("departmentId", warehouse.getDepartmentId());
		return "modules/bv/client/warehouseForm";
	}

	@RequiresPermissions("bv:client:warehouse:edit")
	@RequestMapping(value = "save")
	public String save(Warehouse warehouse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, warehouse)){
			return form(warehouse, model);
		}
		if(StringUtils.isEmpty(warehouse.getDepartmentId())){
			addMessage(model, "参数异常，请联系管理员");
			return form(warehouse, model);
		}
		warehouse.setCreateTime(new Date());
		warehouse.setUpdateTime(new Date());
		warehouseService.save(warehouse);
		addMessage(redirectAttributes, "保存仓库管理成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/warehouse/?repage";
	}
	
	@RequiresPermissions("bv:client:warehouse:edit")
	@RequestMapping(value = "delete")
	public String delete(Warehouse warehouse, RedirectAttributes redirectAttributes) {
		warehouseService.delete(warehouse);
		addMessage(redirectAttributes, "删除仓库管理成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/warehouse/?repage";
	}

}