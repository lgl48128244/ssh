package com.market.project.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.market.project.model.Bill;
import com.market.project.model.Supplier;
import com.market.project.service.BillServiceI;
import com.market.project.service.SupplierServiceI;
import com.market.project.util.ActionUtil;
import com.market.project.util.HqlFilter;

@Namespace("/bill")
@Action
public class BillAction extends BaseAction<Bill> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7532848594112386488L;
	@Autowired
	private BillServiceI billService;
	@Autowired
	private SupplierServiceI supplierService;
	/**
	 * list
	 * @return
	 */
	public void list() {
		List<Bill> list = billService.find();
		writeJson(list);
	}

	/**
	 * addParam
	 * @return
	 */
	public String addParam() {
		List<Supplier> supplierList = supplierService.getSuppliers();
		ServletActionContext.getRequest().setAttribute("supplierList", supplierList);
		return SUCCESS;
	}

	/**
	 * updateParam
	 * @return
	 */
	public String updateParam() {
		List<Supplier> supplierList = supplierService.getSuppliers();
		ServletActionContext.getRequest().setAttribute("supplierList", supplierList);
		Bill bill = billService.getById(id);
		ServletActionContext.getRequest().setAttribute("bill", bill);
		return SUCCESS;
	}

	/**
	 * add
	 * @return
	 * @throws IOException 
	 */
	public String add() {
		try {
			data.setTradeTime(new Date());
			billService.save(data);
			this.clearErrorsAndMessages();
			this.addActionMessage("添加成功");
			ActionUtil.setUrl("/bill_list.action");
		} catch (Exception e) {
			// TODO: handle exception
			this.clearErrorsAndMessages();
			this.addActionMessage("添加失败");
			ActionUtil.setUrl("/bill_list.action");
		}
		return ActionUtil.REDIRECT;
	}

	/**
	 * delete
	 * @return
	 * @throws IOException 
	 */
	/*public String delete() {
		try {
			Bill bill = billService.getById(id);
			billService.delete(bill);
			this.clearErrorsAndMessages();
			this.addActionMessage("删除成功");
			ActionUtil.setUrl("/bill_list.action");
		} catch (Exception e) {
			// TODO: handle exception
			this.clearErrorsAndMessages();
			this.addActionMessage("删除失败");
			ActionUtil.setUrl("/bill_list.action");
		}
		return ActionUtil.REDIRECT;
	}*/

	/**
	 * updateBill
	 * @return
	 * @throws IOException 
	 */
	/*public String update() {
		try {
			data.setTradeTime(new Date());
			billService.update(data);
			this.clearErrorsAndMessages();
			this.addActionMessage("更新成功");
			ActionUtil.setUrl("/bill_list.action");
		} catch (Exception e) {
			// TODO: handle exception
			this.clearErrorsAndMessages();
			this.addActionMessage("更新失败");
			ActionUtil.setUrl("/bill_list.action");
		}
		return ActionUtil.REDIRECT;
	}*/
	
}
