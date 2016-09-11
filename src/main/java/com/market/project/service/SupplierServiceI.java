package com.market.project.service;

import java.util.List;

import com.market.project.model.Supplier;
import com.market.project.util.Pager;

public interface SupplierServiceI {
	public Pager<Supplier> findAll(String sname, String desc);

	public void save(Supplier supplier);

	public void delete(Integer id);

	public void update(Supplier supplier);

	public Supplier updateParam(Integer id);
	
	public List<Supplier> getSuppliers();

}
