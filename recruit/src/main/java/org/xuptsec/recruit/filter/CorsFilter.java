package org.xuptsec.recruit.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mu on 2017/8/7.
 */
public class CorsFilter implements Filter {
    private final static Logger LOGGER= LoggerFactory.getLogger(CorsFilter.class);
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 解决前后端跨域问题
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("CorsFilter doFilter start");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods","GET, POST, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age","36000");
        filterChain.doFilter(servletRequest,response);
    }

    public void destroy() {

    }
}
