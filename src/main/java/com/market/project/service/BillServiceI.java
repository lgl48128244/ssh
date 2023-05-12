package com.market.project.service;

import com.market.project.model.Bill;
import com.market.project.util.Pager;

public interface BillServiceI extends BaseServiceI<Bill> {
    Pager<Bill> findAll(Bill bill, Integer page, Integer rows);
}
