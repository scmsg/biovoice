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
import com.thinkgem.jeesite.modules.bv.entity.client.CustomerNode;
import com.thinkgem.jeesite.modules.bv.service.client.CustomerNodeService;

/**
 * 客户节点Controller
 * @author jinxi
 * @version 2018-01-07
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/client/customerNode")
public class CustomerNodeController extends BaseController {

	@Autowired
	private CustomerNodeService customerNodeService;
	
	@ModelAttribute
	public CustomerNode get(@RequestParam(required=false) String id) {
		CustomerNode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = customerNodeService.get(id);
		}
		if (entity == null){
			entity = new CustomerNode();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:client:customerNode:view")
	@RequestMapping(value = {"list", ""})
	public String list(CustomerNode customerNode, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("customerNode: "+JacksonBundle.nonNullMapper().toJson(customerNode));
		Page<CustomerNode> page = customerNodeService.findPage(new Page<CustomerNode>(request, response), customerNode); 
		model.addAttribute("page", page);
		if(StringUtils.isNotEmpty(customerNode.getUsePlaceId())){
			model.addAttribute("usePlaceId", customerNode.getUsePlaceId());
		}
		if(StringUtils.isNotEmpty(customerNode.getUsePlaceType())){
			model.addAttribute("usePlaceType", customerNode.getUsePlaceType());
		}
		return "modules/bv/client/customerNodeList";
	}

	@RequiresPermissions("bv:client:customerNode:view")
	@RequestMapping(value = "form")
	public String form(CustomerNode customerNode, Model model) {
		model.addAttribute("customerNode", customerNode);
		model.addAttribute("usePlaceId", customerNode.getUsePlaceId());
		model.addAttribute("usePlaceType", customerNode.getUsePlaceType());
		return "modules/bv/client/customerNodeForm";
	}

	@RequiresPermissions("bv:client:customerNode:edit")
	@RequestMapping(value = "save")
	public String save(CustomerNode customerNode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, customerNode)){
			return form(customerNode, model);
		}
		if(StringUtils.isEmpty(customerNode.getUsePlaceId())){
			addMessage(model, "参数异常，请联系管理员!");
			return form(customerNode, model);
		}
		if(StringUtils.isEmpty(customerNode.getUsePlaceType())){
			addMessage(model, "参数异常，请联系管理员!!");
			return form(customerNode, model);
		}
		if(StringUtils.isEmpty(customerNode.getId())){
			customerNode.setCreateTime(new Date());
		}
		customerNode.setUpdateTime(new Date());
		customerNodeService.save(customerNode);
		addMessage(redirectAttributes, "保存客户节点成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/customerNode/?repage";
	}
	
	@RequiresPermissions("bv:client:customerNode:edit")
	@RequestMapping(value = "delete")
	public String delete(CustomerNode customerNode, RedirectAttributes redirectAttributes) {
		customerNodeService.delete(customerNode);
		addMessage(redirectAttributes, "删除客户节点成功");
		return "redirect:"+Global.getAdminPath()+"/bv/client/customerNode/?repage";
	}

}