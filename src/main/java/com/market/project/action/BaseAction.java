package com.market.project.action;

import com.market.project.service.BaseServiceI;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * 基础ACTION,其他ACTION继承此ACTION来获得writeJson和ActionSupport的功能
 * 
 * 基本的CRUD已实现，子类继承BaseAction的时候，提供setService方法即可
 * 
 * 注解@Action后，访问地址就是命名空间+类名(全小写，并且不包括Action后缀)，本action的访问地址就是/base.sy
 * 
 * @author 孙宇
 * 
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	protected Serializable id;// 主键
	protected String ids;// 主键集合，逗号分割
	protected T data;// 数据模型(与前台表单name相同，name="data.xxx")

	protected BaseServiceI<T> service;// 业务逻辑

	/**
	 * 继承BaseAction的action需要先设置这个方法，使其获得当前action的业务服务
	 *
	 * @param service
	 */
	public void setService(BaseServiceI<T> service) {
		this.service = service;
	}

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public T getModel() {
		return data;
	}

	/**
	 * 获得request
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得response
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获得session
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
}