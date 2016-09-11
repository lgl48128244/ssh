package com.market.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_admin")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户编号
	private Integer upk;
	//用户姓名
	private String username;
	//用户性别
	private Integer gender;
	//用户年龄
	private Integer age;
	//用户电话
	private String phone;
	//用户地址
	private String address;
	//权限
	private String role;
	//密码
	private String password;

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	public Integer getUpk() {
		return upk;
	}

	public void setUpk(Integer upk) {
		this.upk = upk;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
