/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONArray;
import org.apache.commons.lang3.time.DateFormatUtils;
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
		logger.info("nodeOfDataList nodeOfData: "+JacksonBundle.nonNullMapper().toJson(nodeOfData));
//		Page<NodeOfData> page = nodeOfDataService.findPage(new Page<NodeOfData>(request, response), nodeOfData); 
		Page<NodeOfData> page = nodeOfDataService.findPageGroubByNodeId(new Page<NodeOfData>(request, response), nodeOfData);
		model.addAttribute("page", page);
		model.addAttribute("nodeOfData", nodeOfData);
		return "modules/bv/nodeOfDataList";
	}
	
	@RequiresPermissions("bv:nodeOfData:view")
	@RequestMapping(value = "charts")
	public String charts(String startTime,String endTime,  NodeOfData nodeOfData, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("nodeOfDataCharts nodeOfData: "+JacksonBundle.nonNullMapper().toJson(nodeOfData));
		if(null == nodeOfData || null == nodeOfData.getNodeId() || nodeOfData.getNodeId() == 0){
			logger.info("请输入你要查看的节点ID，请检查~~");
			addMessage(model, "请输入你要查看的节点ID，请检查");
			logger.info("开始时间"+startTime);
			logger.info("开始时间"+endTime);
			//return "modules/bv/bvMap";
			return "modules/bv/nodeOfDataCharts";
		}
		long stime=0;
		long etime=0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(null != startTime)  stime = simpleDateFormat.parse(startTime).getTime()/1000;
			if(null != endTime)  etime = simpleDateFormat.parse(endTime).getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info("开始时间"+stime);
		logger.info("结束时间"+etime);
		Page<NodeOfData> page = nodeOfDataService.findByStartTimeToEndTime(stime,etime,new Page<NodeOfData>(request, response), nodeOfData);
		logger.info("已取出数据");
		model.addAttribute("page", page);
		model.addAttribute("nodeOfData", nodeOfData);
		
		List<NodeOfData> nodeOfDatas = page.getList();
		if(nodeOfDatas != null && nodeOfDatas.size() > 0){
			List<String> timeTags = new ArrayList<String>();
			List<Short> datas = new ArrayList<Short>();
			for(NodeOfData data : nodeOfDatas){
				if(null == data.getTimeTag() || data.getTimeTag() == 0
						|| null == data.getTemperature() || data.getTemperature() == 0){
					continue;
				}
				timeTags.add(DateFormatUtils.format(data.getTimeTag() * 1000, "yyyy-MM-dd HH:mm:ss"));
				datas.add(data.getTemperature());
			}
			
			//以下数据是为了支持HighCharts而提供的变量
			model.addAttribute("chart", "line");
			model.addAttribute("title", "节点测量数据");
			model.addAttribute("subtitle", "节点明细");
			//坐标
			JSONArray timeTagsArray = new JSONArray(timeTags);
			model.addAttribute("xAxis", timeTagsArray);//测量时间
			model.addAttribute("yAxis", "测量温度值");//测量温度值
			
			model.addAttribute("nodeId", nodeOfData.getNodeId());
			model.addAttribute("start",startTime);
			model.addAttribute("end",endTime);
			JSONArray datasArray = new JSONArray(datas);
			model.addAttribute("datas", datasArray);
		}else{
			logger.info("节点ID暂无测量数据，请检查~~");
			addMessage(model, "节点ID暂无测量数据，请检查");
		}
				
		return "modules/bv/nodeOfDataCharts";
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