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
import com.thinkgem.jeesite.modules.bv.entity.client.Depatement;
import com.thinkgem.jeesite.modules.bv.service.client.DepatementService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 部门Controller
 * @author jinxi
 * @version 2018-01-07
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/client/depatement")
public class DepatementController extends BaseController {

	@Autowired
	private DepatementService depatementService;
	
	@ModelAttribute
	public Depatement get(@RequestParam(required=false) String id) {
		Depatement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = depatementService.get(id);
		}
		if (entity == null){
			entity = new Depatement();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:client:depatement:view")
	@RequestMapping(value = {"list", ""})
	public String list(Depatement depatement, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("depatement: "+JacksonBundle.nonNullMapper().toJson(depatement));
		if(StringUtils.isEmpty(depatement.getCompanyId())){
			depatement.setCompanyId(UserUtils.getUser().getCompany().getId());
		}
		if(StringUtils.isEmpty(depatement.getCompanyName())){
			depatement.setCompanyName(UserUtils.getUser().getCompany().getName());
		}
		Page<Depatement> page = depatementService.findPage(new Page<Depatement>(request, response), depatement); 
		model.addAttribute("page", page);
		model.addAttribute("companyId", depatement.getCompanyId());
		model.addAttribute("companyName", depatement.getCompanyName());
		return "modules/bv/client/depatementList";
	}

	@RequiresPermissions("bv:client:depatement:view")
	@RequestMapping(value = "form")
	public String form(Depatement depatement, Model model) {
		model.addAttribute("depatement", depatement);
		return "modules/bv/client/depatementForm";
	}

	@RequiresPermissions("bv:client:depatement:edit")
	@RequestMapping(value = "save")
	public String save(Depatement depatement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, depatement)){
			return form(depatement, model);
		}
		if(StringUtils.isEmpty(depatement.getCompanyId())){
			depatement.setCompanyId(UserUtils.getUser().getCompany().getId());
		}
		if(StringUtils.isEmpty(depatement.getCompanyName())){
			depatement.setCompanyName(UserUtils.getUser().getCompany().getName());
		}
		if(StringUtils.isEmpty(depatement.getId())){
			depatement.setCreateTime(new Date());
		}
		depatement.setUpdateTime(new Date());
		depatementService.save(depatement);
		addMessage(redirectAttributes, "保存部门成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/depatement/?repage";
	}
	
	@RequiresPermissions("bv:client:depatement:edit")
	@RequestMapping(value = "delete")
	public String delete(Depatement depatement, RedirectAttributes redirectAttributes) {
		depatementService.delete(depatement);
		addMessage(redirectAttributes, "删除部门成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/depatement/?repage";
	}

}