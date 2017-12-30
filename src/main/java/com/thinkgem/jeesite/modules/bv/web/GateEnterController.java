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
import com.thinkgem.jeesite.modules.bv.dto.GateDto;
import com.thinkgem.jeesite.modules.bv.entity.Gate;
import com.thinkgem.jeesite.modules.bv.entity.GateEnter;
import com.thinkgem.jeesite.modules.bv.service.GateEnterService;
import com.thinkgem.jeesite.modules.bv.service.GateService;

/**
 * 网关入库Controller
 * @author jinxi
 * @version 2017-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/gateEnter")
public class GateEnterController extends BaseController {

	@Autowired
	private GateEnterService gateEnterService;
	
	@Autowired
	private GateService gateService;
	
	@ModelAttribute
	public GateEnter get(@RequestParam(required=false) String id) {
		GateEnter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gateEnterService.get(id);
		}
		if (entity == null){
			entity = new GateEnter();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:gateEnter:view")
	@RequestMapping(value = {"list", ""})
	public String list(GateEnter gateEnter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GateEnter> page = gateEnterService.findPage(new Page<GateEnter>(request, response), gateEnter); 
		model.addAttribute("page", page);
		return "modules/bv/gateEnterList";
	}

	@RequiresPermissions("bv:gateEnter:view")
	@RequestMapping(value = "form")
	public String form(GateEnter gateEnter, Model model) {
		model.addAttribute("gateEnter", gateEnter);
		return "modules/bv/gateEnterForm";
	}

	@RequiresPermissions("bv:gateEnter:edit")
	@RequestMapping(value = "save")
	public String save(GateEnter gateEnter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gateEnter)){
			return form(gateEnter, model);
		}
		//校验开始ID和结束ID是否已存在
		Long startId = gateEnter.getStartId();
		Long endId = gateEnter.getStartId() + gateEnter.getEnterNumber() - 1;
		Date enterTime = gateEnter.getEnterTime();
		if(null == startId || startId == 0
				|| null == endId || endId == 0
						|| null == enterTime){
			addMessage(model, "参数异常，请检查");
			return form(gateEnter, model);
		}
		gateEnter.setEndId(endId);
		
		GateDto dto = new GateDto();
		dto.setStartId(startId);
		dto.setEndId(endId);
		
		List<Gate> gates = gateService.findBetweenStartAndEnd(dto);
		if(gates != null && gates.size() > 0){
			addMessage(model, "起始ID和数量组成的节点ID已存在");
			return form(gateEnter, model);
		}
		
		Gate gate = null;
		for(Long gateId = startId; gateId <= endId; gateId++ ){
			gate = new Gate();
			gate.setGateId(gateId);
			gate.setEnterTime(gateEnter.getEnterTime());
			
			gate.setIsAllocated(0);
			gateService.save(gate);
		}
		
		gateEnterService.save(gateEnter);
		addMessage(redirectAttributes, "保存网关入库成功");
		return "redirect:"+Global.getAdminPath()+"/bv/gate/?repage";
	}
	
	@RequiresPermissions("bv:gateEnter:edit")
	@RequestMapping(value = "delete")
	public String delete(GateEnter gateEnter, RedirectAttributes redirectAttributes) {
		gateEnterService.delete(gateEnter);
		addMessage(redirectAttributes, "删除网关入库成功");
		return "redirect:"+Global.getAdminPath()+"/bv/gateEnter/?repage";
	}

}