package com.market.project.action;

import com.market.project.model.User;
import com.market.project.service.UserServiceI;
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
public class UserAction extends ActionSupport implements ModelDriven<User> {

    @Autowired
    private UserServiceI userService;

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


    private List<User> list;
    private User user = new User();
    private Integer id;
    private Pager<User> pager;

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pager<User> getPager() {
        return pager;
    }

    public void setPager(Pager<User> pager) {
        this.pager = pager;
    }

    @Override
    public User getModel() {
        return user;
    }

    @Override
    public String execute() {
        System.out.println(user);
        return SUCCESS;
    }

    /**
     * list
     */
    public String list() {
        this.clearErrorsAndMessages();
        Pager<User> list = userService.findAll(user,1,10);
        getRequest().setAttribute("userList", list);
        return "list";
    }

    /**
     * add
     */
    public String add() {
        try {
            user.setCreateTime(new Date());
            userService.save(user);
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
            userService.delete(userService.getById(id));
            this.clearErrorsAndMessages();
            this.addActionMessage("删除成功");
        } catch (Exception e) {
            this.clearErrorsAndMessages();
            this.addActionMessage("删除失败");
        }
        return ActionUtil.REDIRECT;
    }

    public String toUpdate() {
        User user = userService.getById(id);
        getRequest().setAttribute("user", user);
        return "toUpdate";
    }

    /**
     * 更新一个对象
     */
    public String update() {
        try {
            user.setUpdateTime(new Date());
            userService.update(user);
            this.clearErrorsAndMessages();
            this.addActionMessage("更新成功");
        } catch (Exception e) {
            this.clearErrorsAndMessages();
            this.addActionMessage("更新失败");
        }
        return ActionUtil.REDIRECT;
    }
}