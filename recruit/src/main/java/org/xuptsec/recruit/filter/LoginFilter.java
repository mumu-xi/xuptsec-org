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
public class LoginFilter implements HandlerInterceptor {
    /**
     * 将前端json格式的token取出来，与服务器端session内的token先比较，如果相等并且session中有已登录用户信息
     * 那么放行，若token不匹配或session无用户信息，则跳转至登录页
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //将json字符串转换为json对象
        JSONObject json = JSONUtil.stringToJSONObject(httpServletRequest);
        if (json != null) {
            //获取json中token
            String requestToken = json.getString("token");
            if (requestToken != null) {
                HttpSession session = httpServletRequest.getSession();
                Manager manager = (Manager) session.getAttribute("manager");
                //判断用户信息是否存在
                if (manager != null) {
                    //使用加密的key来取出session中的token
                    String tokenKey = MD5Util.md5(manager.getUsername() + manager.getPassword() + BASE64Util.toBASE64(manager.getSalt()));
                    System.out.println("开始过滤tokenKey: "+tokenKey);
                    String tokenValue = (String) session.getAttribute(tokenKey);
                    System.out.println("开始过滤tokenValue: "+tokenValue);
                    //判断token是否一致
                    if (requestToken.trim().equals(tokenValue.trim())) {
                        //判断成功删除session中token
                        session.removeAttribute(tokenKey);
                        //放行
                        return true;
                    }
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
        //若session中有用户信息，则往客户端发送token以及将token放入session
        if (manager != null) {
            //生成加密key，以便存入token
            String tokenKey = MD5Util.md5(manager.getUsername() + manager.getPassword() + BASE64Util.toBASE64(manager.getSalt()));
            String tokenValue = BASE64Util.toBASE64(manager.getUsername() + UUID.randomUUID() + manager.getPassword() + MD5Util.md5(System.currentTimeMillis() + ""));
            //当base64超过64个长度，会自动插入\r|\n，会导致抛出异常，所以替换其为空
            tokenValue=tokenValue.replaceAll("\r|\n", "");
            //将token放入session
            session.setAttribute(tokenKey, tokenValue);
            //将token放入cookies发送至客户端
            Cookie cookies = new Cookie("token", tokenValue);
            cookies.setPath("/");
            //设置cookies过期时间
            cookies.setMaxAge(900);
            httpServletResponse.addCookie(cookies);

        }
    }
}
