package edu.shily.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Shily-zhang
 * @Description
 */
//@WebFilter("/demo01.do")
//@WebFilter("*.do")通配符，拦截所有请求
public class Demo01Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("helloA1");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("helloA2");
    }

    @Override
    public void destroy() {
    }
}
