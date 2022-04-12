package edu.shily.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Shily-zhang
 * @Description
 */
@WebFilter("*.do")
public class Filter03 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("C1");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("C2");
    }
}
