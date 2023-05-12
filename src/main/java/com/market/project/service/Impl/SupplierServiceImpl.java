package com.market.project.service.Impl;

import com.market.project.model.Supplier;
import com.market.project.service.SupplierServiceI;
import com.market.project.util.Pager;
import com.market.project.util.SystemContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liglo
 */
@Service("supplierService")
public class SupplierServiceImpl extends BaseServiceImpl<Supplier> implements SupplierServiceI {

    private String getParams(Supplier model, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder("where 1=1\n");
        String sname = model.getSname();
        if (StringUtils.isNotBlank(sname)) {
            sb.append("and sname like :sname\n");
            params.put("sname", "%" + sname + "%");
        }
        String description = model.getDescription();
        if (StringUtils.isNotBlank(description)) {
            sb.append("and description like :description\n");
            params.put("description", "%" + description + "%");
        }
        return sb.toString();
    }

    @Override
    public Pager<Supplier> findAll(Supplier supplier, Integer page, Integer rows) {
        int pageSize = SystemContext.getPageSize();
        int pageOffset = SystemContext.getPageOffset();
        if (pageOffset == 0) {
            pageOffset = 1;
        } else {
            pageOffset = (pageOffset / pageSize) + 1;
        }
        Map<String, Object> params = new HashMap<>(2);
        String hql = "from Supplier\t" + getParams(supplier, params) + "\norder by createTime desc";
        List<Supplier> bills = find(hql, params, pageOffset, pageSize);
        hql = "select count(1) from Supplier\t" + getParams(supplier, params);
        Long count = count(hql, params);

        Pager<Supplier> pager = new Pager<>();
        pager.setOffset(pageOffset);
        pager.setPageSize(pageSize);
        pager.setRecords(bills);
        pager.setTotal(count);
        return pager;
    }

    @Override
    public List<Supplier> getSuppliers() {
        return find("from Supplier");
    }
}