package com.market.project.action;

import com.market.project.model.Demo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author liglo
 */
public class DemoAction extends ActionSupport implements ModelDriven<Demo> {

    private List<Demo> list;
    private Demo demo = new Demo();

    //列表展示
    public String list() {
        list = Demo.list();
        return "list";
    }

    //删除操作
    public String delete() {
        Demo.remove(demo);
        return "delete";
    }

    //添加跳转
    public String toAdd() {
        return "toAdd";
    }

    //添加操作
    public String add() {
        Demo.add(demo);
        return "add";
    }

    //更新前回显
    public String toUpdate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String id = request.getParameter("id");
        request.setAttribute("demo", Demo.getById(Integer.parseInt(id)));
        return "toUpdate";
    }

    //更新操作
    public String update() {
        Demo.update(demo);
        return "update";
    }

    public List<Demo> getList() {
        return list;
    }

    public void setList(List<Demo> list) {
        this.list = list;
    }

    public Demo getDemo() {
        return demo;
    }

    public void setDemo(Demo demo) {
        this.demo = demo;
    }

    @Override
    public Demo getModel() {
        return demo;
    }
}