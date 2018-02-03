/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bv.dao.client;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bv.dto.NodeOfDataDto;
import com.thinkgem.jeesite.modules.bv.entity.NodeOfData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 节点测量数据DAO接口
 * @author jinxi
 * @version 2017-12-30
 */
@MyBatisDao
public interface CustomerNodeOfDataDao extends CrudDao<NodeOfData> {

	List<NodeOfData> findCustomerNodeOfDataGroupByCustomerNodeId(@Param(value = "nodeOfDataDto")NodeOfDataDto nodeOfDataDto,@Param(value = "usePlaceId")String usePlaceId);
	List<NodeOfData> findCustomerNodeOfDataByStartTimeToEndTime(@Param(value = "stime") Long stime, @Param(value = "etime") Long etime, @Param(value = "nodeOfDataDto") NodeOfDataDto nodeOfDataDto,@Param(value = "usePlaceType")String usePlaceType,@Param(value = "usePlaceId") String usePlaceId);
	
}