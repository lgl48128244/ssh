package com.market.project.action;

import com.market.project.model.Supplier;
import com.market.project.service.SupplierServiceI;
import com.market.project.util.ActionUtil;
import com.market.project.util.Pager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author liglo
 */
public class SupplierAction extends ActionSupport implements ModelDriven<Supplier> {
    @Autowired
    private SupplierServiceI supplierService;

    /**
     * 获得request
     */
    public HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    /**
     * 获得response
     */
    public HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    private List<Supplier> list;
    private Supplier supplier = new Supplier();
    private Integer id;
    private Pager<Supplier> pager;

    public List<Supplier> getList() {
        return list;
    }

    public void setList(List<Supplier> list) {
        this.list = list;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pager<Supplier> getPager() {
        return pager;
    }

    public void setPager(Pager<Supplier> pager) {
        this.pager = pager;
    }

    @Override
    public String execute() throws Exception {
        System.out.println(supplier);
        return super.execute();
    }

    @Override
    public Supplier getModel() {
        return supplier;
    }

    /**
     * list
     */
    public String list() {
        Pager<Supplier> supplierList = supplierService.findAll(supplier,1,10);
        getRequest().setAttribute("supplierList", supplierList);
        return "list";
    }

    /**
     * toAdd
     */
    public String toAdd() {
        return "toAdd";
    }

    /**
     * add
     */
    public String add() {
        try {
        	supplier.setCreateTime(new Date());
            supplierService.save(supplier);
            this.clearErrorsAndMessages();
            this.addActionMessage("添加成功");
        } catch (Exception e) {
            this.clearErrorsAndMessages();
            this.addActionMessage("添加失败");
        }
        return ActionUtil.REDIRECT;
    }

    /**
     * delete
     */
    public String delete() {
        try {
            supplierService.delete(supplierService.getById(id));
            this.clearErrorsAndMessages();
            this.addActionMessage("删除成功");
        } catch (Exception e) {
            this.clearErrorsAndMessages();
            this.addActionMessage("删除失败");
        }
        return ActionUtil.REDIRECT;
    }

    /**
     * toUpdate
     */
    public String toUpdate() {
        Supplier supplier = supplierService.getById(id);
        getRequest().setAttribute("supplier", supplier);
        return "toUpdate";
    }

    /**
     * update
     */
    public String update() {
        try {
        	supplier.setUpdateTime(new Date());
            supplierService.update(supplier);
            this.clearErrorsAndMessages();
            this.addActionMessage("更新成功");
        } catch (Exception e) {
            this.clearErrorsAndMessages();
            this.addActionMessage("更新失败");
        }
        return ActionUtil.REDIRECT;
    }
}