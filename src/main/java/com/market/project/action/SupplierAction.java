package com.market.project.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.market.project.model.Supplier;
import com.market.project.service.SupplierServiceI;
import com.market.project.util.ActionUtil;
import com.market.project.util.Pager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class SupplierAction extends ActionSupport implements ModelDriven<Supplier> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3918748492886028018L;
	@Autowired
	private SupplierServiceI supplierService;
	private Supplier supplier;
	private String id;
	private String providerName;
	private Pager<Supplier> pager;

	public Pager<Supplier> getPager() {
		return pager;
	}

	public void setPager(Pager<Supplier> pager) {
		this.pager = pager;
	}

	/**
	 * list
	 * @return
	 */
	public String list() {
		Pager<Supplier> supplierList = supplierService.findAll(supplier.getSname(), supplier.getDescription());
		ServletActionContext.getRequest().setAttribute("supplierList", supplierList);
		return SUCCESS;
	}

	/**
	 * add
	 * @return
	 * @throws IOException 
	 */
	public String add() {
		try {
			supplierService.save(supplier);
			this.clearErrorsAndMessages();
			this.addActionMessage("添加成功");
			ActionUtil.setUrl("/supplier_list.action");
		} catch (Exception e) {
			// TODO: handle exception
			this.clearErrorsAndMessages();
			this.addActionMessage("添加失败");
			ActionUtil.setUrl("/supplier_list.action");
		}
		return ActionUtil.REDIRECT;
	}

	/**
	 * delete
	 * @return
	 * @throws IOException 
	 */
	public String delete() {
		try {
			supplierService.delete(Integer.valueOf(id));
			this.clearErrorsAndMessages();
			this.addActionMessage("删除成功");
			ActionUtil.setUrl("/supplier_list.action");
		} catch (Exception e) {
			// TODO: handle exception
			this.clearErrorsAndMessages();
			this.addActionMessage("删除失败");
			ActionUtil.setUrl("/supplier_list.action");
		}
		return ActionUtil.REDIRECT;
	}

	/**
	 * updateParam
	 */
	public String updateParam() {
		Supplier supplier = supplierService.updateParam(Integer.valueOf(id));
		ServletActionContext.getRequest().setAttribute("supplier", supplier);
		return SUCCESS;
	}

	/**
	 * update
	 * @return
	 * @throws IOException 
	 */
	public String update() {
		try {
			supplierService.update(supplier);
			this.clearErrorsAndMessages();
			this.addActionMessage("更新成功");
			ActionUtil.setUrl("/supplier_list.action");
		} catch (Exception e) {
			// TODO: handle exception
			this.clearErrorsAndMessages();
			this.addActionMessage("更新失败");
			ActionUtil.setUrl("/supplier_list.action");
		}
		return ActionUtil.REDIRECT;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public Supplier getModel() {
		// TODO Auto-generated method stub
		supplier = new Supplier();
		return supplier;
	}
}
