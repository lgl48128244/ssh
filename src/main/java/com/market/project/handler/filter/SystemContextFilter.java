package com.market.project.handler.filter;

import com.market.project.util.SystemContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author liglo
 */
public class SystemContextFilter implements Filter {
    private int pageSize;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            int tps = pageSize;
            try {
                tps = Integer.parseInt(req.getParameter("pageSize"));
            } catch (NumberFormatException ignored) {
            }
            int pageOffset = 0;
            try {
                pageOffset = Integer.parseInt(req.getParameter("pager.offset"));
            } catch (NumberFormatException ignored) {
            }
            SystemContext.setPageOffset(pageOffset);
            SystemContext.setPageSize(tps);
            chain.doFilter(req, resp);
        } finally {
            SystemContext.removePageOffset();
            SystemContext.removePageSize();
        }
    }

    @Override
    public void init(FilterConfig cfg) throws ServletException {
        pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
    }
}