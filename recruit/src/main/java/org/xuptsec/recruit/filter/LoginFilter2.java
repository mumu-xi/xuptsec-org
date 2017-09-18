package org.xuptsec.recruit.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.xuptsec.recruit.poji.Manager;
import org.xuptsec.recruit.poji.ResultJoin;
import org.xuptsec.recruit.utils.BASE64Util;
import org.xuptsec.recruit.utils.JSONUtil;
import org.xuptsec.recruit.utils.MD5Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by mu on 2017/9/10.
 */
public class LoginFilter2 implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {


            String requestToken = httpServletRequest.getParameter("token");
            if (requestToken != null) {
                HttpSession session = httpServletRequest.getSession();
                System.out.println("过滤器 sessionId: "+session.getId());

                Manager manager = (Manager) session.getAttribute("manager");
                if (manager != null) {

                    String tokenKey = MD5Util.md5(manager.getUsername() + manager.getPassword() + BASE64Util.toBASE64(manager.getSalt()));
                    String tokenValue = (String) session.getAttribute(tokenKey);
                    if (requestToken.trim().equals(tokenValue.trim())) {
                        session.removeAttribute(tokenKey);
                        return true;
                    }
                }
            }

        httpServletResponse.setContentType("text/html; charset=UTF-8");
        ResultJoin resultJoin = new ResultJoin("false","身份验证失败，需重新登录！");
        httpServletResponse.getWriter().write(JSONUtil.objectToJSONString(resultJoin));
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        System.out.println(session.getId());
        if ( manager != null) {
            String tokenKey = MD5Util.md5(manager.getUsername() + manager.getPassword() + BASE64Util.toBASE64(manager.getSalt()));
            String tokenValue = BASE64Util.toBASE64(manager.getUsername() + UUID.randomUUID() + manager.getPassword() + MD5Util.md5(System.currentTimeMillis() + ""));

            tokenValue=tokenValue.replaceAll("\r|\n", "");
            session.setAttribute(tokenKey, tokenValue);
            Cookie cookies = new Cookie("token", tokenValue);
            cookies.setPath("/");
            cookies.setMaxAge(900);
            httpServletResponse.addCookie(cookies);

        }
    }
}
