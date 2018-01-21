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
import com.thinkgem.jeesite.common.utils.JacksonBundle;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bv.entity.client.UsePlace;
import com.thinkgem.jeesite.modules.bv.service.client.UsePlaceService;

/**
 * 使用场所Controller
 * @author jinxi
 * @version 2018-01-21
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/client/usePlace")
public class UsePlaceController extends BaseController {

	@Autowired
	private UsePlaceService usePlaceService;
	
	@ModelAttribute
	public UsePlace get(@RequestParam(required=false) String id) {
		UsePlace entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = usePlaceService.get(id);
		}
		if (entity == null){
			entity = new UsePlace();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:client:usePlace:view")
	@RequestMapping(value = {"list", ""})
	public String list(UsePlace usePlace, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("warehouse: "+JacksonBundle.nonNullMapper().toJson(usePlace));
		Page<UsePlace> page = usePlaceService.findPage(new Page<UsePlace>(request, response), usePlace); 
		model.addAttribute("page", page);
		if(StringUtils.isNotEmpty(usePlace.getDepartmentId())){
			model.addAttribute("departmentId", usePlace.getDepartmentId());
		}
		return "modules/bv/client/usePlaceList";
	}

	@RequiresPermissions("bv:client:usePlace:view")
	@RequestMapping(value = "form")
	public String form(UsePlace usePlace, Model model) {
		model.addAttribute("usePlace", usePlace);
		return "modules/bv/client/usePlaceForm";
	}

	@RequiresPermissions("bv:client:usePlace:edit")
	@RequestMapping(value = "save")
	public String save(UsePlace usePlace, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, usePlace)){
			return form(usePlace, model);
		}
		usePlaceService.save(usePlace);
		addMessage(redirectAttributes, "保存使用场所成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/usePlace/?repage";
	}
	
	@RequiresPermissions("bv:client:usePlace:edit")
	@RequestMapping(value = "delete")
	public String delete(UsePlace usePlace, RedirectAttributes redirectAttributes) {
		usePlaceService.delete(usePlace);
		addMessage(redirectAttributes, "删除使用场所成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/usePlace/?repage";
	}

}