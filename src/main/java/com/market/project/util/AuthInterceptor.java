package com.market.project.util;

import org.springframework.stereotype.Component;

import com.market.project.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Component
public class AuthInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3891720893753650602L;

	public String intercept(ActionInvocation invocation) throws Exception {
		//1、获取action的名称
		User user = (User) ActionContext.getContext().getSession().get("user");
		//1、获取action的名称
		String an = invocation.getProxy().getActionName();
		if (!an.startsWith("login.action")) {
			if (user == null)
				return "login";
			//来进行其他权限控制
			if (user.getRole() != "admin权限") {
				//普通用户
				if (!ActionUtil.checkUrl(an)) {
					throw new MarketException("需要管理员才能访问该功能");
				}
			}
		}
		return invocation.invoke();
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}
}
