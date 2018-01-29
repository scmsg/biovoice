/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bv.entity.Node;
import com.thinkgem.jeesite.modules.bv.service.NodeService;

/**
 * 节点信息Controller
 * @author jinxi
 * @version 2017-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/node")
public class NodeController extends BaseController {

	@Autowired
	private NodeService nodeService;
	static Logger log = Logger.getLogger(NodeController.class);
	@ModelAttribute
	public Node get(@RequestParam(required=false) String id) {
		Node entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nodeService.get(id);
		}
		if (entity == null){
			entity = new Node();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:node:view")
	@RequestMapping(value = {"list", ""})
	public String list(Node node, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Node> page = nodeService.findPage(new Page<Node>(request, response), node); 
		model.addAttribute("page", page);
		return "modules/bv/nodeList";
	}
	@RequiresPermissions("bv:node:view")
	@RequestMapping(value = "ajax")
	public void ajax(Node node, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> str = nodeService.findPageNodeId(); 
		String json = JSONArray.toJSONString(str);
		try {
			
			log.debug("#########################################################################################################");
			log.debug(json);
			log.debug("###############################################################################################################");
			response.getWriter().write(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequiresPermissions("bv:node:view")
	@RequestMapping(value = "form")
	public String form(Node node, Model model) {
		model.addAttribute("node", node);
		return "modules/bv/nodeForm";
	}

	@RequiresPermissions("bv:node:edit")
	@RequestMapping(value = "save")
	public String save(Node node, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, node)){
			return form(node, model);
		}
		nodeService.save(node);
		addMessage(redirectAttributes, "保存节点信息成功");
		return "redirect:"+Global.getAdminPath()+"/bv/node/?repage";
	}
	
	@RequiresPermissions("bv:node:edit")
	@RequestMapping(value = "delete")
	public String delete(Node node, RedirectAttributes redirectAttributes) {
		nodeService.delete(node);
		addMessage(redirectAttributes, "删除节点信息成功");
		return "redirect:"+Global.getAdminPath()+"/bv/node/?repage";
	}

}