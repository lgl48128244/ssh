package com.market.project.handler.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * 字符集编码拦截器
 *
 * @author liglo
 */
public class EncodingInterceptor extends MethodFilterInterceptor {

    @Override
    public String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //ActionContext actionContext = actionInvocation.getInvocationContext();
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        ServletActionContext.getRequest().setCharacterEncoding("utf-8");
        return actionInvocation.invoke();
    }
}