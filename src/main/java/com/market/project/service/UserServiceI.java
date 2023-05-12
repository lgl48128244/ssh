package com.market.project.service;

import com.market.project.model.User;
import com.market.project.util.Pager;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface UserServiceI extends BaseServiceI<User> {

    User login(String uname, String pwd);

    void updatePwd(Integer upk, String password);

    Pager<User> findAll(User user, Integer page, Integer rows);
}