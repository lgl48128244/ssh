package com.market.project.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SystemContextFilter implements Filter {
	private int pageSize;

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		try {
			int tps = pageSize;
			try {
				tps = Integer.parseInt(req.getParameter("pageSize"));
			} catch (NumberFormatException e) {
			}
			int pageOffset = 0;
			try {
				pageOffset = Integer.parseInt(req.getParameter("pager.offset"));
			} catch (NumberFormatException e) {
			}
			SystemContext.setPageOffset(pageOffset);
			SystemContext.setPageSize(tps);
			chain.doFilter(req, resp);
		} finally {
			SystemContext.removePageOffset();
			SystemContext.removePageSize();
		}
	}

	public void init(FilterConfig cfg) throws ServletException {
		pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
	}
}
