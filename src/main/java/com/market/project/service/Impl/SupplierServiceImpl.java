package com.market.project.service.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.project.dao.BaseDaoI;
import com.market.project.model.Supplier;
import com.market.project.service.SupplierServiceI;
import com.market.project.util.Pager;

@Service("supplierService")
public class SupplierServiceImpl extends BaseService implements SupplierServiceI {
	@Autowired
	private BaseDaoI<Supplier> baseDao;

	@Override
	public Pager<Supplier> findAll(String sname, String desc) {
		// TODO Auto-generated method stub
		Pager<Supplier> pager = null;
		String hql = "";
		if (StringUtils.isNotBlank(sname) && StringUtils.isBlank(desc)) {
			hql = "from Supplier where sname like ?";
			Object[] params = { "%" + sname + "%" };
			pager = baseDao.query(hql, params);
		} else if (StringUtils.isBlank(sname) && StringUtils.isNotBlank(desc)) {
			hql = "from Supplier where description like ?";
			Object[] params = { "%" + desc + "%" };
			pager = baseDao.query(hql, params);
		} else if (StringUtils.isNotBlank(sname) && StringUtils.isNotBlank(desc)) {
			hql = "from Supplier where sname like ? or description like ?";
			Object[] params = { "%" + sname + "%", "%" + desc + "%" };
			pager = baseDao.query(hql, params);
		} else {
			hql = "from Supplier";
			pager = baseDao.query(hql);
		}
		return pager;
	}

	@Override
	public void save(Supplier supplier) {
		// TODO Auto-generated method stub
		baseDao.save(supplier);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Supplier supplie = baseDao.getById(Supplier.class, id);
		baseDao.delete(supplie);
	}

	@Override
	public void update(Supplier supplier) {
		// TODO Auto-generated method stub
		baseDao.update(supplier);
	}

	@Override
	public Supplier updateParam(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.getById(Supplier.class, id);
	}

	@Override
	public List<Supplier> getSuppliers() {
		// TODO Auto-generated method stub
		return baseDao.find("from Supplier");
	}
}
