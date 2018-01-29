/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.bv.entity.client.Depatement;
import com.thinkgem.jeesite.modules.bv.entity.client.Equipment;
import com.thinkgem.jeesite.modules.bv.entity.client.Trucks;
import com.thinkgem.jeesite.modules.bv.entity.client.Warehouse;
import com.thinkgem.jeesite.modules.bv.service.client.DepatementService;
import com.thinkgem.jeesite.modules.bv.service.client.EquipmentService;
import com.thinkgem.jeesite.modules.bv.service.client.TrucksService;
import com.thinkgem.jeesite.modules.bv.service.client.WarehouseService;
import com.thinkgem.jeesite.modules.sys.dao.AreaDao;
import com.thinkgem.jeesite.modules.sys.dao.MenuDao;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.dao.RoleDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.thinkgem.jeesite.modules.sys.vo.ZtreeNode;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);
	
//	@Autowired
//	private DepatementService depatementService;
//	@Autowired
//	private WarehouseService warehouseService;
//	@Autowired
//	private EquipmentService equipmentService;
//	@Autowired
//	private TrucksService trucksService;
	
	private static DepatementService depatementService = SpringContextHolder.getBean(DepatementService.class);
	private static WarehouseService warehouseService = SpringContextHolder.getBean(WarehouseService.class);
	private static EquipmentService equipmentService = SpringContextHolder.getBean(EquipmentService.class);
	private static TrucksService trucksService = SpringContextHolder.getBean(TrucksService.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
	
	public static final String USER_CACHE_ZTREE_ = "ztree_";
	
	public static final String CACHE_AUTH_INFO = "authInfo";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user ==  null){
			user = userDao.get(id);
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
			
			//校验用户是否只有客户端权限
			
		}
		return user;
	}
	
	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String loginName){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null){
			user = userDao.getByLoginName(new User(null, loginName));
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_AUTH_INFO);
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_OFFICE_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
		if (user.getOffice() != null && user.getOffice().getId() != null){
			CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
		}
		CacheUtils.remove(USER_CACHE, USER_CACHE_ZTREE_ + user.getId());
	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		Principal principal = getPrincipal();
		if (principal!=null){
			User user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	public static List<Role> getRoleList(){
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>)getCache(CACHE_ROLE_LIST);
		if (roleList == null){
			User user = getUser();
			if (user.isAdmin()){
				roleList = roleDao.findAllList(new Role());
			}else{
				Role role = new Role();
				role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
				roleList = roleDao.findList(role);
			}
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<Menu> getMenuList(){
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			User user = getUser();
			if (user.isAdmin()){
				menuList = menuDao.findAllList(new Menu());
			}else{
				Menu m = new Menu();
				m.setUserId(user.getId());
				menuList = menuDao.findByUserId(m);
			}
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}
	
	/**
	 * 获取当前用户授权的区域
	 * @return
	 */
	public static List<Area> getAreaList(){
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>)getCache(CACHE_AREA_LIST);
		if (areaList == null){
			areaList = areaDao.findAllList(new Area());
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_LIST);
		if (officeList == null){
			User user = getUser();
			if (user.isAdmin()){
				officeList = officeDao.findAllList(new Office());
			}else{
				Office office = new Office();
				office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				officeList = officeDao.findList(office);
			}
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}
	
	/**
	 * 普通用户，获取树型菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<ZtreeNode>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  getZtreeNodeList(){
		User user = getUser();
		List<ZtreeNode> ztreeNodes = (List<ZtreeNode>) CacheUtils.get(USER_CACHE, USER_CACHE_ZTREE_ + user.getId());
		
		if(null == ztreeNodes || ztreeNodes.size() == 0){
			ztreeNodes = getZtreeNodeList(user);
		}
		
		if(ztreeNodes != null && ztreeNodes.size() > 0){
			CacheUtils.put(USER_CACHE, USER_CACHE_ZTREE_ + user.getId(), ztreeNodes);
		}
		return ztreeNodes;
	}
	
	private static List<ZtreeNode> getZtreeNodeList(User user){
		Office company = user.getCompany();
		
		String companyId = company.getId();
		
		Depatement depatement = new Depatement();
		depatement.setCompanyId(companyId);
		List<Depatement> depatements = depatementService.findList(depatement);
		
		List<ZtreeNode> ztreeNodes = new ArrayList<ZtreeNode>();
		ZtreeNode ztreeNode = null;
		ztreeNode = new ZtreeNode();
		ztreeNode.setId(company.getId());
		ztreeNode.setPId(company.getParentId());
		ztreeNode.setName(company.getName());
		ztreeNode.setFile("/bv/client/depatement");
		ztreeNode.setOpen("true");
		ztreeNodes.add(ztreeNode);
		
		if(depatements != null && depatements.size() > 0){
			for(Depatement dep : depatements){
				ztreeNode = new ZtreeNode();
				ztreeNode.setId(dep.getId());
				ztreeNode.setPId(company.getId());
				ztreeNode.setName(dep.getDeptName());
				//链接跳到所有仓库/设备/车辆的信息列表
				ztreeNode.setFile("/bv/client/usePlace/list?departmentId="+dep.getId());
				ztreeNode.setOpen("false");
				ztreeNodes.add(ztreeNode);
				
				//dept == >> Equipment\Warehouse\Trucks
				Warehouse warehouse = new Warehouse();
				warehouse.setDepartmentId(dep.getId());
				List<Warehouse> warehouseList = warehouseService.findList(warehouse);
				if(warehouseList != null && warehouseList.size() > 0){
					for(Warehouse w : warehouseList){
						ztreeNode = new ZtreeNode();
						ztreeNode.setId(w.getId());
						ztreeNode.setPId(dep.getId());
						ztreeNode.setName(w.getWarehouseName());
						ztreeNode.setFile("/bv/client/customerNode/list?usePlaceId="+w.getId()+"&usePlaceType=1");
						ztreeNode.setOpen("false");
						ztreeNodes.add(ztreeNode);
					}
				}
				Equipment equipment = new Equipment();
				equipment.setDepartmentId(dep.getId());
				List<Equipment> equipmentlist = equipmentService.findList(equipment);
				if(equipmentlist != null && equipmentlist.size() > 0){
					for(Equipment e : equipmentlist){
						ztreeNode = new ZtreeNode();
						ztreeNode.setId(e.getId());
						ztreeNode.setPId(dep.getId());
						ztreeNode.setName(e.getEquipmentName());
						ztreeNode.setFile("/bv/client/customerNode/list?usePlaceId="+e.getId()+"&usePlaceType=2");
						ztreeNode.setOpen("false");
						ztreeNodes.add(ztreeNode);
					}
				}

				Trucks trucks = new Trucks();
				trucks.setDepartmentId(dep.getId());
				List<Trucks> trucksList = trucksService.findList(trucks);
				if(trucksList != null && trucksList.size() > 0){
					for(Trucks t : trucksList){
						ztreeNode = new ZtreeNode();
						ztreeNode.setId(t.getId());
						ztreeNode.setPId(dep.getId());
						ztreeNode.setName(t.getPlateNumber());
						ztreeNode.setFile("/bv/client/customerNode/list?usePlaceId="+t.getId()+"&usePlaceType=3");
						ztreeNode.setOpen("false");
						ztreeNodes.add(ztreeNode);
					}
				}
			}
		}
		return ztreeNodes;
	}

	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeAllList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null){
			officeList = officeDao.findAllList(new Office());
		}
		return officeList;
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
//			subject.logout();
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
//		getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}
	
//	public static Map<String, Object> getCacheMap(){
//		Principal principal = getPrincipal();
//		if(principal!=null){
//			return principal.getCacheMap();
//		}
//		return new HashMap<String, Object>();
//	}
	
}
