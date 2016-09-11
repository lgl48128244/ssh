package com.market.project.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.market.project.model.SystemConfig;
import com.market.project.model.User;
import com.market.project.service.SystemConfigI;
import com.market.project.service.UserServiceI;
import com.market.project.util.ActionUtil;
import com.market.project.util.BeanUtils;
import com.market.project.util.Pager;
@Namespace("/user")
@Action
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 6871148737058952391L;
	@Autowired
	private UserServiceI userService;
	@Autowired
	private SystemConfigI systemConfig;
	
	
	/**
	 * save
	 * 
	 * @return
	 * @throws IOException
	 */
	public String add() {
		try {
			if (data != null) {
				service.save(data);
			}
			this.clearErrorsAndMessages();
			this.addActionMessage("添加成功");
			ActionUtil.setUrl("/user_list.action");
		} catch (Exception e) {
			// TODO: handle exception
			this.clearErrorsAndMessages();
			this.addActionMessage("添加失败");
			ActionUtil.setUrl("/user_list.action");
		}
		return ActionUtil.REDIRECT;
	}

	/**
	 * login
	 * 
	 * @return
	 * @throws IOException
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
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String nowDate = sdf.format(new Date());
					SystemConfig config = new SystemConfig();
					config.setId("LastLoginDate");
					config.setValue(nowDate);
					systemConfig.updateSystemConfig(config);
					return "login_index";
				}
			}
		}
		return ERROR;
	}

	/**
	 * logout
	 * 
	 * @return
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		ActionUtil.setUrl("login.jsp?type=2");
		return ActionUtil.REDIRECT;
	}

	/**
	 * list
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String list() {
		this.clearErrorsAndMessages();
		HttpServletRequest request = ServletActionContext.getRequest();
		Pager<User> list = userService.list(data.getUsername());
		request.setAttribute("userList", list);
		return SUCCESS;
	}

	/**
	 * delete
	 * 
	 * @return
	 * @throws IOException
	 */
	/*public String delete() {
		try {
			if (!StringUtils.isBlank(id)) {
				User user = service.getById(id);
				service.delete(user);
			}
			this.clearErrorsAndMessages();
			this.addActionMessage("删除成功");
			ActionUtil.setUrl("/user_list.action");
		} catch (Exception e) {
			// TODO: handle exception
			this.clearErrorsAndMessages();
			this.addActionMessage("删除失败");
			ActionUtil.setUrl("/user_list.action");
		}
		return ActionUtil.REDIRECT;
	}*/

	/**
	 * updatePWS
	 * 
	 * @return
	 * @throws IOException
	 */
	public String updatePWD() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Integer id = Integer.valueOf(request.getParameter("upk"));
			String pwd = request.getParameter("password");
			userService.updatePWD(id, pwd);
			ActionUtil.setUrl("/login.jsp?type=4");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ActionUtil.REDIRECT;
	}

	/**
	 * beforeupdateUser
	 * 
	 * @return
	 */
	public String updateUser() {
		User user = userService.updateUser(data.getUpk());
		ServletActionContext.getRequest().setAttribute("user", user);
		return Action.DEFAULT_VALUE;
	}
	
	/**
	 * 更新一个对象
	 */
	public String updateResult() {
		String reflectId = null;
		try {
			if (data != null) {
				reflectId = (String) FieldUtils.readField(data, "upk", true);
			}
			if (!StringUtils.isBlank(reflectId)) {
				User user = service.getById(reflectId);
				BeanUtils.copyNotNullProperties(data, user, new String[] { "upk" });
				service.update(user);
			}
			this.clearErrorsAndMessages();
			this.addActionMessage("更新成功");
			ActionUtil.setUrl("/user_list.action");
		} catch (Exception e) {
			// TODO: handle exception
			this.clearErrorsAndMessages();
			this.addActionMessage("更新失败");
			ActionUtil.setUrl("/user_list.action");
		}
		return ActionUtil.REDIRECT;
	}
}
