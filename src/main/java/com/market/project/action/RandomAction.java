package com.market.project.action;

import java.io.ByteArrayInputStream;

import org.apache.struts2.ServletActionContext;

import com.market.project.util.RandomNumUtil;
import com.opensymphony.xwork2.ActionSupport;
public class RandomAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -622273358051753182L;
	private ByteArrayInputStream inputStream;

	public String code() throws Exception {
		RandomNumUtil rdnu = RandomNumUtil.Instance();
		this.setInputStream(rdnu.getImage());//取得带有随机字符串的图片    
		ServletActionContext.getRequest().getSession().setAttribute("sessionCode", rdnu.getString());
		//ActionContext.getContext().getSession().put("sessionCode", rdnu.getString());//取得随机字符串放入HttpSession    
		return "code";
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
}
