/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web.client;

import java.util.Date;
import java.util.List;

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
import com.thinkgem.jeesite.modules.bv.entity.client.Equipment;
import com.thinkgem.jeesite.modules.bv.entity.client.Trucks;
import com.thinkgem.jeesite.modules.bv.entity.client.UsePlace;
import com.thinkgem.jeesite.modules.bv.service.client.TrucksService;
import com.thinkgem.jeesite.modules.bv.service.client.UsePlaceService;

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
	
	@Autowired
	private UsePlaceService usePlaceService;
	
	@ModelAttribute
	public Trucks get(@RequestParam(required=false) String id, @RequestParam(required=false) String usePlaceId) {
		Trucks entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = trucksService.get(id);
		}else if(StringUtils.isNotBlank(usePlaceId)){
			Trucks trucks = new Trucks();
			trucks.setUsePlaceId(usePlaceId);
			List<Trucks> trucksList = trucksService.findList(trucks);
			if(trucksList != null && trucksList.size() == 1){
				entity = trucksList.get(0);
			}
		}
		if (entity == null){
			entity = new Trucks();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:client:trucks:view")
	@RequestMapping(value = {"list", ""})
	public String list(Trucks trucks, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("trucks: "+JacksonBundle.nonNullMapper().toJson(trucks));
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
		Date nowDate = new Date();
		trucks.setCreateTime(nowDate);
		trucks.setUpdateTime(nowDate);
		
		if(StringUtils.isEmpty(trucks.getId())){
			trucks.setCreateTime(nowDate);
		}
		trucks.setUpdateTime(nowDate);
		
		//保存主表
		UsePlace usePlace = new UsePlace();
		if(StringUtils.isEmpty(trucks.getUsePlaceId())){
			//保存主表
			usePlace.setCreateTime(nowDate);
		}else{
			usePlace.setId(trucks.getUsePlaceId());
			usePlace = usePlaceService.get(usePlace);
		}
		
		usePlace.setName(trucks.getPlateNumber());
		usePlace.setManagerId(trucks.getManagerId());
		usePlace.setWarningPhone(trucks.getDriverContact());
		usePlace.setAlarmPhone(trucks.getDriverContact());
		
		usePlace.setDepartmentId(trucks.getDepartmentId());
		usePlace.setUsePlaceType(3);
		usePlace.setUpdateTime(nowDate);
		
		//usePlaceService.save(usePlace);
		usePlace.setUsePlaceId(usePlace.getId());
		usePlaceService.save(usePlace);
		
		trucks.setUsePlaceId(usePlace.getId());
		trucksService.save(trucks);
		addMessage(redirectAttributes, "保存车辆管理成功");
		//return "redirect:"+Global.getAdminPath()+"/bv/client/trucks/list?departmentId="+trucks.getDepartmentId();
		return "redirect:"+Global.getAdminPath()+"/bv/client/usePlace/list?departmentId="+trucks.getDepartmentId();
	}
	
	@RequiresPermissions("bv:client:trucks:edit")
	@RequestMapping(value = "delete")
	public String delete(Trucks trucks, RedirectAttributes redirectAttributes) {
		trucksService.delete(trucks);
		addMessage(redirectAttributes, "删除车辆管理成功");
		//return "redirect:"+Global.getAdminPath()+"/bv/client/trucks/?repage";
		return "redirect:"+Global.getAdminPath()+"/bv/client/usePlace/list?departmentId="+trucks.getDepartmentId();
	}

}