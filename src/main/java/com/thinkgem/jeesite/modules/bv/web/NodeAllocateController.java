/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.bv.entity.Customer;
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
import com.thinkgem.jeesite.modules.bv.entity.Gate;
import com.thinkgem.jeesite.modules.bv.entity.Node;
import com.thinkgem.jeesite.modules.bv.entity.NodeAllocate;
import com.thinkgem.jeesite.modules.bv.service.GateService;
import com.thinkgem.jeesite.modules.bv.service.NodeAllocateService;
import com.thinkgem.jeesite.modules.bv.service.NodeService;

/**
 * 节点分配Controller
 * @author jinxi
 * @version 2017-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/nodeAllocate")
public class NodeAllocateController extends BaseController {

	@Autowired
	private NodeAllocateService nodeAllocateService;
	
	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private GateService gateService;
	
	@ModelAttribute
	public NodeAllocate get(@RequestParam(required=false) String id) {
		NodeAllocate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nodeAllocateService.get(id);
		}
		if (entity == null){
			entity = new NodeAllocate();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:nodeAllocate:view")
	@RequestMapping(value = {"list", ""})
	public String list( NodeAllocate nodeAllocate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NodeAllocate> page = nodeAllocateService.findPage(new Page<NodeAllocate>(request, response), nodeAllocate); 
		model.addAttribute("page", page);
		return "modules/bv/nodeAllocateList";
	}

	@RequiresPermissions("bv:nodeAllocate:view")
	@RequestMapping(value = "form")
	public String form(NodeAllocate nodeAllocate, Model model) {
		model.addAttribute("nodeAllocate", nodeAllocate);
		return "modules/bv/nodeAllocateForm";
	}

	@RequiresPermissions("bv:nodeAllocate:edit")
	@RequestMapping(value = "save")
	public String save(NodeAllocate nodeAllocate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nodeAllocate)){
			return form(nodeAllocate, model);
		}
		
		if(null == nodeAllocate.getNodeData() || nodeAllocate.getNodeData().length() == 0){
			addMessage(model, "分配的节点不能为空，请检查");
			return form(nodeAllocate, model);
		}
/*		if(null == nodeAllocate.getGateData() || nodeAllocate.getGateData().length() == 0){
			addMessage(model, "分配的网关不能为空，请检查");
			return form(nodeAllocate, model);
		}*/
		
		//更新节点信息
		String nodeDataList = nodeAllocate.getNodeData();
		String[] nodeDatas = nodeDataList.split(",");
		Node node = null;
		Node tempNode = null;
		for(String nodeData : nodeDatas){
			Long nodeId = 0L;
			try{
				nodeId = Long.valueOf(nodeData);
			}catch(Exception e){
				e.printStackTrace();
				addMessage(model, "分配的节点："+nodeData+", 是无效数据，请检查");
				return form(nodeAllocate, model);
			}
			
			node = new Node();
			node.setNodeId(nodeId);
			tempNode = nodeService.getByNodeId(node);
			if(null == tempNode){
				addMessage(model, "分配的节点："+nodeData+", 存在无效数据，请检查");
				return form(nodeAllocate, model);
			}else{
				if(tempNode.getIsAllocated() == 1){
					addMessage(model, "分配的节点："+nodeData+", 已被分配过，请检查");
					return form(nodeAllocate, model);
				}
			}
		}
		Gate gate = null;
		Gate tempGate = null;
		String[] gateDatas=null;
		//更新网关信息
		if(null != nodeAllocate.getGateData() && nodeAllocate.getGateData().length() != 0) {
			String gateDataList = nodeAllocate.getGateData();
			gateDatas = gateDataList.split(",");
/*			Gate gate = null;
			Gate tempGate = null;*/
			for (String gateData : gateDatas) {
				Long gateId = 0L;
				try {
					gateId = Long.valueOf(gateData);
				} catch (Exception e) {
					e.printStackTrace();
					addMessage(model, "分配的网关：" + gateData + ", 是无效数据，请检查");
					return form(nodeAllocate, model);
				}

				gate = new Gate();
				gate.setGateId(gateId);
				tempGate = gateService.getByGateId(gate);
				if (null == tempGate) {
					addMessage(model, "分配的网关：" + gateData + ", 存在无效数据，请检查");
					return form(nodeAllocate, model);
				} else {
					if (tempGate.getIsAllocated() == 1) {
						addMessage(model, "分配的网关：" + gateData + ", 已被分配过，请检查");
						return form(nodeAllocate, model);
					}
				}
			}
		}
		for(String nodeData : nodeDatas){
			Long nodeId = Long.valueOf(nodeData);
			node = new Node();
			node.setNodeId(nodeId);
			node.setIsAllocated(1);
			node.setCustomerId(nodeAllocate.getCompanyId());
			nodeService.updateAllocated(node);
		}
		if(null != nodeAllocate.getGateData() && nodeAllocate.getGateData().length() != 0) {
			for (String gateData : gateDatas) {
				Long gateId = Long.valueOf(gateData);
				gate = new Gate();
				gate.setGateId(gateId);
				gate.setIsAllocated(1);
				gate.setCustomerId(nodeAllocate.getCompanyId());
				gateService.updateAllocated(gate);
			}
		}
		nodeAllocateService.save(nodeAllocate);
		addMessage(redirectAttributes, "保存节点分配成功");
		return "redirect:"+Global.getAdminPath()+"/bv/nodeAllocate/?repage";
	}
	
	@RequiresPermissions("bv:nodeAllocate:edit")
	@RequestMapping(value = "delete")
	public String delete(NodeAllocate nodeAllocate, RedirectAttributes redirectAttributes) {
		nodeAllocateService.delete(nodeAllocate);
		addMessage(redirectAttributes, "删除节点分配成功");
		return "redirect:"+Global.getAdminPath()+"/bv/nodeAllocate/?repage";
	}

}