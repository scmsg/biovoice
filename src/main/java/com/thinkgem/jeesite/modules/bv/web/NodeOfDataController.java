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
import com.thinkgem.jeesite.modules.bv.entity.NodeOfData;
import com.thinkgem.jeesite.modules.bv.service.NodeOfDataService;

/**
 * 节点测量数据Controller
 * @author jinxi
 * @version 2017-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/nodeOfData")
public class NodeOfDataController extends BaseController {

	@Autowired
	private NodeOfDataService nodeOfDataService;
	
	@ModelAttribute
	public NodeOfData get(@RequestParam(required=false) String id) {
		NodeOfData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nodeOfDataService.get(id);
		}
		if (entity == null){
			entity = new NodeOfData();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:nodeOfData:view")
	@RequestMapping(value = {"list", ""})
	public String list(NodeOfData nodeOfData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NodeOfData> page = nodeOfDataService.findPage(new Page<NodeOfData>(request, response), nodeOfData); 
		model.addAttribute("page", page);
		return "modules/bv/nodeOfDataList";
	}

	@RequiresPermissions("bv:nodeOfData:view")
	@RequestMapping(value = "form")
	public String form(NodeOfData nodeOfData, Model model) {
		model.addAttribute("nodeOfData", nodeOfData);
		return "modules/bv/nodeOfDataForm";
	}

	@RequiresPermissions("bv:nodeOfData:edit")
	@RequestMapping(value = "save")
	public String save(NodeOfData nodeOfData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nodeOfData)){
			return form(nodeOfData, model);
		}
		nodeOfDataService.save(nodeOfData);
		addMessage(redirectAttributes, "保存节点测量数据成功");
		return "redirect:"+Global.getAdminPath()+"/bv/nodeOfData/?repage";
	}
	
	@RequiresPermissions("bv:nodeOfData:edit")
	@RequestMapping(value = "delete")
	public String delete(NodeOfData nodeOfData, RedirectAttributes redirectAttributes) {
		nodeOfDataService.delete(nodeOfData);
		addMessage(redirectAttributes, "删除节点测量数据成功");
		return "redirect:"+Global.getAdminPath()+"/bv/nodeOfData/?repage";
	}

}