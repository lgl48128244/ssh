package com.market.project.service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import com.market.project.model.User;
import com.market.project.util.Pager;

@WebService
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface UserServiceI {

	public User login(String uname, String pwd);

	public void updatePWD(Integer upk, String password);

	public User save(User user);

	public Pager<User> list(String userName);

	public void update(User u);

	public void delete(Integer id);

	public User updateUser(Integer id);

}
