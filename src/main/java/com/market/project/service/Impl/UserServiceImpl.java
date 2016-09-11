package com.market.project.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.project.dao.BaseDaoI;
import com.market.project.model.User;
import com.market.project.service.UserServiceI;
import com.market.project.util.Pager;

@Component("userService")
public class UserServiceImpl extends BaseService implements UserServiceI {
	@Autowired
	private BaseDaoI<User> baseDAO;

	@Override
	public User login(String username, String password) {
		String hql = "from User u where u.username =:username and u.password =:password";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		return (User) baseDAO.getByHql(hql, params);
	}

	@Override
	public User save(User user) {
		logger.info("保存用户信息");
		try {
			User u = (User) baseDAO.save(user);
			if (u != null) {
				return u;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 分页
	 * @param userName
	 * @return
	 */
	@Override
	public Pager<User> list(String userName) {
		// TODO Auto-generated method stub
		Pager<User> pager = null;
		if (StringUtils.isBlank(userName)) {
			pager = baseDAO.query("from User");
		} else {
			pager = baseDAO.query("from User where username like ?", "%" + userName + "%");
		}
		return pager;
	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub
		baseDAO.update(u);
	}

	@Override
	public void updatePWD(Integer upk, String password) {
		// TODO Auto-generated method stub
		User user = baseDAO.getById(User.class, upk);
		user.setPassword(password);
		baseDAO.update(user);
	}

	@Override
	public User updateUser(Integer id) {
		// TODO Auto-generated method stub
		return baseDAO.getById(User.class, id);
	}

	@Override
	public void delete(Integer id) {
		User user = baseDAO.getById(User.class, id);
		baseDAO.delete(user);
	}
}
