/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bv.entity.Customer;
import com.thinkgem.jeesite.modules.bv.service.CustomerService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

import java.io.IOException;
import java.util.List;

import java.io.IOException;
import java.util.List;

/**
 * 客户信息Controller
 * @author jinxi
 * @version 2017-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/bv/customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public Customer get(@RequestParam(required=false) String id) {
		Customer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = customerService.get(id);
		}
		if (entity == null){
			entity = new Customer();
		}
		return entity;
	}
	
	@RequiresPermissions("bv:customer:view")
	@RequestMapping(value = {"list", ""})
	public String list(Customer customer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Customer> page = customerService.findPage(new Page<Customer>(request, response), customer); 
		model.addAttribute("page", page);
		return "modules/bv/customerList";
	}

	@RequiresPermissions("bv:customer:view")
	@RequestMapping(value = {"companyList"})
	public void companyList(Customer customer, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> strs = customerService.findCompanyList();
		String json = JSONArray.toJSONString(strs);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequiresPermissions("bv:customer:view")
	@RequestMapping(value = {"checkCompanyName"})
	public void checkCompanyName(String companyName,HttpServletRequest request, HttpServletResponse response) {
		String str =  customerService.checkCompanyName(companyName);
		if(str !=""&& str !=null) {
			try {
				response.getWriter().write("true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().write("flase");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@RequiresPermissions("bv:customer:view")
	@RequestMapping(value = "form")
	public String form(Customer customer, Model model) {
		model.addAttribute("customer", customer);
		return "modules/bv/customerForm";
	}

	@RequiresPermissions("bv:customer:edit")
	@RequestMapping(value = "save")
	public String save(Customer customer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, customer)){
			addMessage(redirectAttributes, "客户信息验证失败");
			return form(customer, model);
		}
		if(null == customer){
			addMessage(redirectAttributes, "客户信息为空，请重新输入");
			return form(customer, model);
		}
		//新增
		if(StringUtils.isEmpty(customer.getId())){
			if(StringUtils.isEmpty(customer.getCompanyName())){
				addMessage(redirectAttributes, "客户公司为空，请重新输入");
				return form(customer, model);
			}
			if(customerService.checkCompanyName(customer.getCompanyName())!=""&& customerService.checkCompanyName(customer.getCompanyName())!=null){
				addMessage(redirectAttributes, "公司已存在，请重新输入");
				return form(customer, model);
			}
			if(StringUtils.isEmpty(customer.getAdminAccount())){
				addMessage(redirectAttributes, "根管理账号为空，请重新输入");
				return form(customer, model);
			}
			if(StringUtils.isEmpty(customer.getAdminAccount())){
				addMessage(redirectAttributes, "根管理密码，请重新输入");
				return form(customer, model);
			}
			//1.sys_office
			Office office_old = officeService.get(String.valueOf(17));
			Office office = new Office();
			BeanUtils.copyProperties(office_old, office);
			office.setId(null);
			office.setName(customer.getCompanyName());

			
			//2.sys_user
			User user = new User();
			user.setLoginName(customer.getAdminAccount());
			user.setNo(customer.getAdminAccount());
			user.setName(customer.getCustomerName());
			user.setMobile(customer.getMobile());
			user.setPhone(customer.getMobile());
			
			if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
				addMessage(model, "保存用户'" + user.getLoginName() + "'失败，根管理账号/登录名已存在");
				return form(customer, model);
			}
			
			officeService.save(office);
			user.setCompany(office);
			user.setOffice(office);
			// 角色数据有效性验证，过滤不在授权内的角色
			List<Role> roleList = Lists.newArrayList();
			Role role = systemService.getRoleByName("普通用户");
			if(null == role){
				role = systemService.getRole("6");
			}
			roleList.add(role);
			user.setRoleList(roleList);
			
			// 如果新密码为空，则不更换密码
			if (StringUtils.isNotBlank(customer.getAdminPassword())) {
				user.setPassword(SystemService.entryptPassword(customer.getAdminPassword()));
			}
			if (!beanValidator(model, user)){
				return form(customer, model);
			}
			systemService.saveUser(user);
		}
		
		//3.新增客户
		customer.setId(null);
		customerService.save(customer);
		addMessage(redirectAttributes, "保存客户信息成功");
		return "redirect:"+Global.getAdminPath()+"/bv/customer/?repage";
	 }
	
	@RequiresPermissions("bv:customer:edit")
	@RequestMapping(value = "delete")
	public String delete(Customer customer, RedirectAttributes redirectAttributes) {
		customerService.delete(customer);
		addMessage(redirectAttributes, "删除客户信息成功");
		return "redirect:"+Global.getAdminPath()+"/bv/customer/?repage";
	}
	
	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

}