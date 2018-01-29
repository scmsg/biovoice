package com.thinkgem.jeesite.modules.act.service.creator;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

public interface RuntimeActivityCreator
{
	ActivityImpl[] createActivities(ProcessEngine processEngine, ProcessDefinitionEntity processDefinition,
                                    RuntimeActivityDefinitionEntity info);
}