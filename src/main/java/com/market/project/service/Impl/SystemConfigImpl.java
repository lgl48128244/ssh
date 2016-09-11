package com.market.project.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.project.dao.BaseDaoI;
import com.market.project.model.SystemConfig;
import com.market.project.service.SystemConfigI;

@Service("systemConfig")
public class SystemConfigImpl implements SystemConfigI {
	@Autowired
	private BaseDaoI<SystemConfig> baseDAO;

	@Override
	public void updateSystemConfig(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		baseDAO.update(systemConfig);
	}
}
