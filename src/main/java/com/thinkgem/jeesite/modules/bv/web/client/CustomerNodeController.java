/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web.client;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.thinkgem.jeesite.modules.bv.dao.CustomerDao;
import com.thinkgem.jeesite.modules.bv.entity.Customer;
import com.thinkgem.jeesite.modules.bv.entity.Node;
import com.thinkgem.jeesite.modules.bv.service.NodeService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
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
	private CustomerNodeService  customerNodeService;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private NodeService nodeService;
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
	@RequestMapping(value = "ajax")
	public void ajax(Node node, HttpServletRequest request, HttpServletResponse response, Model model) {
 		Long customerId = UserUtils.getUser().getCustomerId();
		//Office office=null;
		//office=officeService.get(customerId);
		Customer customer= new Customer();
		customer.setId(customerId+"");
		//customer.setCompanyName(office.getName());
		//customer=customerDao.findCompanyIdByCompanyName(customer);
		List<String> str = customerNodeService.findCustomerNodeId(customer);
		String json = JSONArray.toJSONString(str);
		try {
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequiresPermissions("bv:client:customerNode:view")
	@RequestMapping(value = "form")
	public String form(CustomerNode customerNode,Model model) {
		//,String usePlaceId,String usePlaceType
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
		if(null == customerNode){
			addMessage(redirectAttributes, "客户节点信息为空，请重新输入");
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
/*		String customerId = UserUtils.getUser().getCompany().getId();
		Office office=null;
		office=officeService.get(customerId);
		Customer customer= new Customer();
		customer.setCompanyName(office.getName());
		customer=customerDao.findCompanyIdByCompanyName(customer);*/
		String[] nodeDatas = customerNode.getCustomerNodeIds().split(",");
		Node node = null;
		node = new Node();
		Node tempNode = null;
		for(String nodeData : nodeDatas) {
			Long nodeId = 0L;
			try {
				nodeId = Long.valueOf(nodeData);
			}catch(Exception e){
				e.printStackTrace();
				addMessage(model, "绑定的节点："+nodeData+", 是无效数据，请检查");
				return form(customerNode, model);
			}

		node.setNodeId(nodeId);
		tempNode = nodeService.getByNodeId(node);
		if(null == tempNode){
			addMessage(model, "绑定的节点："+nodeData+", 存在无效数据，请检查");
			return form(customerNode, model);
		}else{
			if(tempNode.getBoundStatus() == 1){
				addMessage(model, "绑定的节点："+nodeData+", 已被绑定过，请检查");
				return form(customerNode, model);
			}
		}
	}
		for(String nodeData : nodeDatas){
			Long nodeId = Long.valueOf(nodeData);
			node = new Node();
			node.setNodeId(nodeId);
			customerNode.setNodeId(nodeId);
		//	node.setCustomerId(Long.valueOf(customer.getId()));
			node.setBoundStatus(1);
			nodeService.updateBoundStatus(node);
		if(StringUtils.isEmpty(customerNode.getId())){
			customerNode.setCreateTime(new Date());
		}
		customerNode.setUpdateTime(new Date());
		customerNodeService.save(customerNode);
		}
		addMessage(redirectAttributes, "保存客户节点成功");
		//return "redirect:"+Global.getAdminPath()+"/bv/client/customerNode/?repage";
		//return "redirect:"+Global.getAdminPath()+"/bv/client/customerNode/list?usePlaceId="+usePlaceId+"&usePlaceType="+usePlaceType+";
		return "redirect:"+Global.getAdminPath()+"/bv/client/customerNode/list?usePlaceId="+customerNode.getUsePlaceId()+"&usePlaceType="+customerNode.getUsePlaceType();
	}
	
	@RequiresPermissions("bv:client:customerNode:edit")
	@RequestMapping(value = "delete")
	public String delete(CustomerNode customerNode, RedirectAttributes redirectAttributes) {
		customerNodeService.delete(customerNode);
		addMessage(redirectAttributes, "删除客户节点成功");
		//return "redirect:"+Global.getAdminPath()+"/bv/client/customerNode/?repage";
		return "redirect:"+Global.getAdminPath()+"/bv/client/customerNode/list?usePlaceId="+customerNode.getUsePlaceId()+"&usePlaceType="+customerNode.getUsePlaceType();

	}

}