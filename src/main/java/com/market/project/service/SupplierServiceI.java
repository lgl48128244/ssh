package com.market.project.service;

import com.market.project.model.Supplier;
import com.market.project.util.Pager;

import java.util.List;

public interface SupplierServiceI extends BaseServiceI<Supplier> {
    Pager<Supplier> findAll(Supplier supplier, Integer page, Integer rows);

    List<Supplier> getSuppliers();
}
