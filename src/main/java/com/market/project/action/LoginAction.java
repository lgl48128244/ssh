package com.market.project.action;

import com.market.project.model.SystemConfig;
import com.market.project.model.User;
import com.market.project.service.SystemConfigI;
import com.market.project.service.UserServiceI;
import com.market.project.util.ActionUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class LoginAction extends ActionSupport {

    @Autowired
    private UserServiceI userService;
    @Autowired
    private SystemConfigI systemConfig;

    /**
     * login
     */
    public String login() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");
        String code = request.getParameter("code");
        String sessionCode = (String) request.getSession().getAttribute(
                "sessionCode");
        if (StringUtils.isBlank(code)) {
            return ERROR;
        }
        if (!code.equals(sessionCode)) {
            request.setAttribute("info", "验证码错误！");
            return "error_code";
        } else {
            if (StringUtils.isNotBlank(uname) && StringUtils.isNotBlank(pwd)) {
                User user = userService.login(uname, pwd);
                if (user != null) {
                    ServletActionContext.getRequest().getSession()
                            .setAttribute("user", user);
                    // 登录成功后记录登录时间
                    SystemConfig config = new SystemConfig();
                    config.setId("LastLoginDate");
                    config.setValue(new Date());
                    config.setContent("最后登录时间");
                    systemConfig.updateSystemConfig(config);
                    return "index";
                }
            }
        }
        return ERROR;
    }

    /**
     * logout
     */
    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        ActionUtil.setUrl("login.jsp?type=2");
        return "logout";
    }

    /**
     * updatePwd
     */
    public String updatePwd() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer id = Integer.valueOf(request.getParameter("upk"));
        String pwd = request.getParameter("password");
        userService.updatePwd(id, pwd);
        ActionUtil.setUrl("/login.jsp?type=4");
        request.getSession().invalidate();
        return "logout";
    }
}