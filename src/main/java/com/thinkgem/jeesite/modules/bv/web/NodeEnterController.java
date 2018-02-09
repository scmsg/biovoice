/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bv.dto.NodeDto;
import com.thinkgem.jeesite.modules.bv.entity.Node;
import com.thinkgem.jeesite.modules.bv.entity.NodeEnter;
import com.thinkgem.jeesite.modules.bv.service.NodeEnterService;
import com.thinkgem.jeesite.modules.bv.service.NodeService;

/**
 * 节点入库Controller
 * @author jinxi
 * @version 2017-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/nodeEnter")
public class NodeEnterController extends BaseController {

	@Autowired
	private NodeEnterService nodeEnterService;

	@Autowired
	private NodeService nodeService;
	
	@ModelAttribute
	public NodeEnter get(@RequestParam(required=false) String id) {
		NodeEnter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nodeEnterService.get(id);
		}
		if (entity == null){
			entity = new NodeEnter();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:nodeEnter:view")
	@RequestMapping(value = {"list", ""})
	public String list(NodeEnter nodeEnter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NodeEnter> page = nodeEnterService.findPage(new Page<NodeEnter>(request, response), nodeEnter); 
		model.addAttribute("page", page);
		return "modules/bv/nodeEnterList";
	}

	@RequiresPermissions("bv:nodeEnter:view")
	@RequestMapping(value = "form")
	public String form(NodeEnter nodeEnter, Model model) {
		model.addAttribute("nodeEnter", nodeEnter);
		return "modules/bv/nodeEnterForm";
	}

	@RequiresPermissions("bv:nodeEnter:edit")
	@RequestMapping(value = "save")
	public String save(NodeEnter nodeEnter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nodeEnter)){
			return form(nodeEnter, model);
		}
		//校验开始ID和结束ID是否已存在
		Long startId = nodeEnter.getStartId();
		Long endId = nodeEnter.getStartId() + nodeEnter.getEnterNumber() - 1;
		Date enterTime = nodeEnter.getEnterTime();
		if(null == startId || startId == 0
				|| null == endId || endId == 0
						|| null == enterTime){
			addMessage(model, "参数异常，请检查");
			return form(nodeEnter, model);
		}
		nodeEnter.setEndId(endId);
		
		NodeDto dto = new NodeDto();
		dto.setStartId(startId);
		dto.setEndId(endId);
		
		List<Node> nodes = nodeService.findBetweenStartAndEnd(dto);
		if(nodes != null && nodes.size() > 0){
			addMessage(model, "起始ID和数量组成的节点ID已存在");
			return form(nodeEnter, model);
		}
		
		Node node = null;
		for(Long nodeId = startId; nodeId <= endId; nodeId++ ){
			node = new Node();
			node.setNodeId(nodeId);
			node.setEnterTime(nodeEnter.getEnterTime());
			
			node.setIsAllocated(0);
			node.setBoundStatus(0);
			nodeService.save(node);
		}
		
		nodeEnterService.save(nodeEnter);
		addMessage(redirectAttributes, "保存节点入库成功");
		return "redirect:"+Global.getAdminPath()+"/bv/node/?repage";
	}
	
	@RequiresPermissions("bv:nodeEnter:edit")
	@RequestMapping(value = "delete")
	public String delete(NodeEnter nodeEnter, RedirectAttributes redirectAttributes) {
		nodeEnterService.delete(nodeEnter);
		addMessage(redirectAttributes, "删除节点入库成功");
		return "redirect:"+Global.getAdminPath()+"/bv/nodeEnter/?repage";
	}

}