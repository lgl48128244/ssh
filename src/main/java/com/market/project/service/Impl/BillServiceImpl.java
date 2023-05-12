package com.market.project.service.Impl;

import com.market.project.model.Bill;
import com.market.project.service.BillServiceI;
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
@Service("billService")
public class BillServiceImpl extends BaseServiceImpl<Bill> implements BillServiceI {

    private String getParams(Bill model, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder("where 1=1\n");
        Integer payType = model.getIsPay();
        if (payType != null) {
            sb.append("and isPay=:isPay\n");
            params.put("isPay", payType);
        }
        String productName = model.getProductName();
        if (StringUtils.isNotBlank(productName)) {
            sb.append("and productname like :productname\n");
            params.put("productname", "%" + productName + "%");
        }
        return sb.toString();
    }

    @Override
    public Pager<Bill> findAll(Bill bill, Integer page, Integer rows) {
        int pageSize = SystemContext.getPageSize();
        int pageOffset = SystemContext.getPageOffset();
        if (pageOffset == 0) {
            pageOffset = 1;
        } else {
            pageOffset = (pageOffset / pageSize) + 1;
        }
        Map<String, Object> params = new HashMap<>(2);
        String hql = "from Bill\t" + getParams(bill, params) + "\norder by tradeTime desc";
        List<Bill> bills = find(hql, params, pageOffset, pageSize);
        hql = "select count(1) from Bill\t" + getParams(bill, params);
        Long count = count(hql, params);

        Pager<Bill> pager = new Pager<>();
        pager.setOffset(pageOffset);
        pager.setPageSize(pageSize);
        pager.setRecords(bills);
        pager.setTotal(count);
        return pager;
    }
}