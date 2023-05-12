package com.market.project.handler.interceptor;

import com.market.project.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * session拦截器
 *
 * @author 孙宇
 */
public class SessionInterceptor extends MethodFilterInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User sessionInfo = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        logger.info("进入session拦截器->访问路径为[" + ServletActionContext.getRequest().getServletPath() + "]");
        if (sessionInfo == null) {
            String errMsg = "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！";
            logger.info(errMsg);
            ServletActionContext.getRequest().setAttribute("msg", errMsg);
            return "errorInterceptor";
        }
        return actionInvocation.invoke();
    }
}
