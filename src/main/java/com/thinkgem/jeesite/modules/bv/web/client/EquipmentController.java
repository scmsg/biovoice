/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web.client;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import com.thinkgem.jeesite.modules.bv.entity.client.Equipment;
import com.thinkgem.jeesite.modules.bv.entity.client.UsePlace;
import com.thinkgem.jeesite.modules.bv.service.client.EquipmentService;
import com.thinkgem.jeesite.modules.bv.service.client.UsePlaceService;

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
	
	@Autowired
	private UsePlaceService usePlaceService;
	
	@ModelAttribute
	public Equipment get(@RequestParam(required=false) String id, @RequestParam(required=false) String usePlaceId) {
		Equipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = equipmentService.get(id);
		}else if(StringUtils.isNotBlank(usePlaceId)){
			Equipment equipment = new Equipment();
			equipment.setUsePlaceId(usePlaceId);
			List<Equipment> equipments = equipmentService.findList(equipment);
			if(equipments != null && equipments.size() == 1){
				entity = equipments.get(0);
			}
		}
		if (entity == null){
			entity = new Equipment();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:client:equipment:view")
	@RequestMapping(value = {"list", ""})
	public String list(Equipment equipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("equipment: "+JacksonBundle.nonNullMapper().toJson(equipment));
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
		Date nowDate = new Date();
		if(StringUtils.isEmpty(equipment.getId())){
			equipment.setCreateTime(nowDate);
		}
		equipment.setUpdateTime(nowDate);
		
		//保存主表
		UsePlace usePlace = new UsePlace();
		if(StringUtils.isEmpty(equipment.getUsePlaceId())){
			//保存主表
			usePlace.setCreateTime(nowDate);
		}else{
			usePlace.setId(equipment.getUsePlaceId());
			usePlace = usePlaceService.get(usePlace);
		}
		usePlace.setName(equipment.getEquipmentName());
		usePlace.setManagerId(equipment.getManagerId());
		usePlace.setWarningPhone(equipment.getWarningPhone1());
		usePlace.setAlarmPhone(equipment.getAlarmPhone1());
		usePlace.setMeasuerPeriod(equipment.getMeasuerPeriod());
		
		usePlace.setDepartmentId(equipment.getDepartmentId());
		usePlace.setUsePlaceType(2);
		
		usePlace.setUpdateTime(nowDate);
		
		usePlaceService.save(usePlace);
		usePlace.setUsePlaceId(usePlace.getId());
		usePlaceService.save(usePlace);
		
		equipment.setUsePlaceId(usePlace.getId());
		equipmentService.save(equipment);
		UserUtils.clearZtreeNodeList();
		addMessage(redirectAttributes, "保存设备管理成功");
		//return "redirect:"+Global.getAdminPath()+"/bv/client/equipment/list?departmentId="+equipment.getDepartmentId();
		return "redirect:"+Global.getAdminPath()+"/bv/client/usePlace/list?departmentId="+equipment.getDepartmentId();
	}
	
	@RequiresPermissions("bv:client:equipment:edit")
	@RequestMapping(value = "delete")
	public String delete(Equipment equipment, RedirectAttributes redirectAttributes) {
		UsePlace usePlace=new UsePlace();
		equipmentService.delete(equipment);
		usePlace.setId(equipment.getUsePlaceId());
		usePlaceService.delete(usePlace);
		addMessage(redirectAttributes, "删除设备管理成功");
		//return "redirect:"+Global.getAdminPath()+"/bv/client/equipment/?repage";
		return "redirect:"+Global.getAdminPath()+"/bv/client/usePlace/list?departmentId="+equipment.getDepartmentId();
	}

}