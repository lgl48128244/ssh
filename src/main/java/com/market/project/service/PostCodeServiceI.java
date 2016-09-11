package com.market.project.service;

import java.util.List;

import com.market.project.model.PostCode;

public interface PostCodeServiceI {

	public List<PostCode> queryPostCodes();

	public long count();
}
