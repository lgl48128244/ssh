package com.market.project.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.project.dao.BaseDaoI;
import com.market.project.model.PostCode;
import com.market.project.service.PostCodeServiceI;

@Component("postCodeService")
public class PostCodeServiceImpl implements PostCodeServiceI {

	@Autowired
	private BaseDaoI<PostCode> baseDAO;

	@Override
	public List<PostCode> queryPostCodes() {
		return baseDAO.find("from PostCode");
	}

	public long count() {
		return baseDAO.count("select count(1) from PostCode");
	}
}
