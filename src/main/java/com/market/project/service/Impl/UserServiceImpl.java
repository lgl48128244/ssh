package com.market.project.service.Impl;

import com.market.project.dao.BaseDaoI;
import com.market.project.model.User;
import com.market.project.service.UserServiceI;
import com.market.project.util.Pager;
import com.market.project.util.SystemContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liglo
 */
@Component("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserServiceI {

    @Autowired
    private BaseDaoI<User> baseDAO;

    @Override
    public User login(String username, String password) {
        String hql = "from User u where u.username =:username and u.password =:password";
        Map<String, Object> params = new HashMap<>(2);
        params.put("username", username);
        params.put("password", password);
        return baseDAO.getByHql(hql, params);
    }

    private String getParams(User model, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder("where 1=1\n");
        String username = model.getUsername();
        if (StringUtils.isNotBlank(username)) {
            sb.append("and username like :username\n");
            params.put("username", "%" + username + "%");
        }
        return sb.toString();
    }

    @Override
    public Pager<User> findAll(User user, Integer page, Integer rows) {
        int pageSize = SystemContext.getPageSize();
        int pageOffset = SystemContext.getPageOffset();
        if (pageOffset == 0) {
            pageOffset = 1;
        } else {
            pageOffset = (pageOffset / pageSize) + 1;
        }
        Map<String, Object> params = new HashMap<>(2);
        String hql = "from User\t" + getParams(user, params) + "\norder by createTime desc";
        List<User> users = find(hql, params, pageOffset, pageSize);
        hql = "select count(1) from User\t" + getParams(user, params);
        Long count = count(hql, params);

        Pager<User> pager = new Pager<>();
        pager.setOffset(pageOffset);
        pager.setPageSize(pageSize);
        pager.setRecords(users);
        pager.setTotal(count);
        return pager;
    }

    @Override
    public void updatePwd(Integer upk, String password) {
        User user = baseDAO.getById(User.class, upk);
        user.setPassword(password);
        baseDAO.update(user);
    }
}