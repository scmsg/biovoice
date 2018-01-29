/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.TestAudit;

/**
 * 审批DAO接口
 * @author thinkgem
 * @version 2014-05-16
 */
@MyBatisDao
public interface TestAuditDao extends CrudDao<TestAudit> {

	TestAudit getByProcInsId(String procInsId);
	
	int updateInsId(TestAudit testAudit);
	
	int updateHrText(TestAudit testAudit);
	
	int updateLeadText(TestAudit testAudit);
	
	int updateMainLeadText(TestAudit testAudit);
	
}
