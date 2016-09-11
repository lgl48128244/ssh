package com.market.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="supplier")
public class Supplier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6104660626921235799L;
	//供应商编号
	private int sid;
	//供应商名称
	private String sname;
	//供应商描述
	private String description;
	//联系人
	private String contacter;
	//传真
	private String fax;
	//联系电话
	private String phone;
	//联系地址
	private String address;

	public Supplier() {
		super();
	}

	/**
	 * shift+alt+s+o
	* <p>Title: </p>
	* <p>Description: </p>
	* @param sid
	* @param sname
	* @param description
	* @param contacter
	* @param fax
	* @param phone
	* @param address
	 */
	public Supplier(int sid, String sname, String description, String contacter, String fax, String phone, String address) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.description = description;
		this.contacter = contacter;
		this.fax = fax;
		this.phone = phone;
		this.address = address;
	}

	/**
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Supplier [sid=" + sid + ", sname=" + sname + ", description=" + description + ", contacter=" + contacter + ", fax=" + fax + ", phone=" + phone + ", address=" + address + "]";
	}

	@Id
	@GeneratedValue
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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
}
