package com.market.project.handler.interceptor;

import com.market.project.model.User;
import com.market.project.util.ActionUtil;
import com.market.project.util.MarketException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.springframework.stereotype.Component;

@Component
public class AuthInterceptor implements Interceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        //1、获取action的名称
        User user = (User) ActionContext.getContext().getSession().get("user");
        //1、获取action的名称
        String an = invocation.getProxy().getActionName();
        if (!an.startsWith("user_login.action")) {
            if (user == null) {
                return "login";
            }
            //来进行其他权限控制
            if (!"admin权限".equals(user.getRole())) {
                //普通用户
                if (!ActionUtil.checkUrl(an)) {
                    throw new MarketException("需要管理员才能访问该功能");
                }
            }
        }
        return invocation.invoke();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }
}