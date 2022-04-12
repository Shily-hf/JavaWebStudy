package edu.shily.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Shily-zhang
 * @Description
 */
@WebFilter("*.do")
public class Filter01 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("A1");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("A2");
    }
}
