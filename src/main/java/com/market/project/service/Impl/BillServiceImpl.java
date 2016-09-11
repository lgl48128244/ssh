package com.market.project.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.project.dao.BaseDaoI;
import com.market.project.model.Bill;
import com.market.project.service.BillServiceI;
import com.market.project.util.Pager;

@Service("billService")
public class BillServiceImpl extends BaseServiceImpl<Bill> implements BillServiceI  {

	@Autowired
	private BaseDaoI<Bill> baseDao;
	
	
	@Override
	public Pager<Bill> findAll(String name,int ispay) {
		// TODO Auto-generated method stub
		Pager<Bill> pager = null;
		String hql = "";
		if (StringUtils.isNotBlank(name)) {
			Object[] params = { "%" + name + "%" ,ispay};
			hql = "from Bill where productname like ?";
			pager = baseDao.query(hql, params);
		} else{
			Object[] params = {};
			hql = "from Bill ";
			pager = baseDao.query(hql, params);
		}
		return pager;
	}
}
