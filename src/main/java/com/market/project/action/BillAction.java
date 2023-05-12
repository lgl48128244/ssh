package com.market.project.action;

import com.market.project.model.Bill;
import com.market.project.model.Supplier;
import com.market.project.service.BillServiceI;
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

public class BillAction extends ActionSupport implements ModelDriven<Bill> {
    @Autowired
    private BillServiceI billService;
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


    private List<Bill> list;
    private Bill bill = new Bill();
    private Pager<Bill> pager;
    private Integer id;

    public List<Bill> getList() {
        return list;
    }

    public void setList(List<Bill> list) {
        this.list = list;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Pager<Bill> getPager() {
        return pager;
    }

    public void setPager(Pager<Bill> pager) {
        this.pager = pager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Bill getModel() {
        return bill;
    }

    @Override
    public String execute() {
        System.out.println(bill);
        return SUCCESS;
    }

    /**
     * list
     */
    public String list() {
        HttpServletRequest request = getRequest();
        Pager<Bill> billList = billService.findAll(bill, 1, 10);
        request.setAttribute("billList", billList);
        return "list";
    }

    /**
     * toAdd
     */
    public String toAdd() {
        return "toAdd";
    }

    /**
     * toUpdate
     */
    public String toUpdate() {
        List<Supplier> supplierList = supplierService.getSuppliers();
        getRequest().setAttribute("supplierList", supplierList);
        HttpServletRequest request = getRequest();
        System.out.println(id);
        String id = request.getParameter("id");
        Bill bill = billService.getById(Integer.parseInt(id));
        getRequest().setAttribute("bill", bill);
        return "toUpdate";
    }

    /**
     * add
     */
    public String add() {
        try {
            bill.setTradeTime(new Date());
            billService.save(bill);
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
            Bill bill = billService.getById(id);
            billService.delete(bill);
            this.clearErrorsAndMessages();
            this.addActionMessage("删除成功");
        } catch (Exception e) {
            this.clearErrorsAndMessages();
            this.addActionMessage("删除失败");
        }
        return ActionUtil.REDIRECT;
    }

    /**
     * updateBill
     */
    public String update() {
        try {
            bill.setUpdateTime(new Date());
            billService.update(bill);
            this.clearErrorsAndMessages();
            this.addActionMessage("更新成功");
        } catch (Exception e) {
            this.clearErrorsAndMessages();
            this.addActionMessage("更新失败");
        }
        return ActionUtil.REDIRECT;
    }
}