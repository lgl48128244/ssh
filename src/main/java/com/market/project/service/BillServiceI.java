package com.market.project.service;

import com.market.project.model.Bill;
import com.market.project.util.Pager;

public interface BillServiceI extends BaseServiceI<Bill>{
	public Pager<Bill> findAll(String param1,int param2);
}
