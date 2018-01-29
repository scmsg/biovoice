/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bv.dto.NodeOfDataDto;
import com.thinkgem.jeesite.modules.bv.entity.NodeOfData;
import org.apache.ibatis.annotations.Param;

/**
 * 节点测量数据DAO接口
 * @author jinxi
 * @version 2017-12-30
 */
@MyBatisDao
public interface NodeOfDataDao extends CrudDao<NodeOfData> {
	
	List<NodeOfData> findPageGroubByNodeId( NodeOfDataDto nodeOfDataDto);
	List<NodeOfData> findByStartTimeToEndTime(@Param(value="stime")Long stime,@Param(value = "etime")Long etime,@Param(value = "nodeOfDataDto")NodeOfDataDto nodeOfDataDto);
	
}