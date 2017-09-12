package org.xuptsec.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xuptsec.recruit.poji.Login;
import org.xuptsec.recruit.poji.Manager;
import org.xuptsec.recruit.poji.ResultList;
import org.xuptsec.recruit.poji.ResultLogin;
import org.xuptsec.recruit.service.ManagerService;
import org.xuptsec.recruit.utils.BASE64Util;
import org.xuptsec.recruit.utils.MD5Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by mu on 2017/9/10.
 */
@Controller
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public @ResponseBody ResultLogin managerLogin(HttpServletResponse response,HttpSession  session, @RequestBody Login login){
        ResultLogin result = new ResultLogin();
        try {
            Manager manager = managerService.managerLogin(login.getUsername() , login.getPassword());
            if(manager!=null){
                session.setAttribute("manager",manager);
                session.setMaxInactiveInterval(900);
                String tokenKey = MD5Util.md5(manager.getUsername() + manager.getPassword() + BASE64Util.toBASE64(manager.getSalt()));
                String tokenValue = BASE64Util.toBASE64(manager.getUsername() + UUID.randomUUID() + manager.getPassword() + MD5Util.md5(System.currentTimeMillis() + ""));
                tokenValue=tokenValue.replaceAll("\r|\n", "");
                session.setAttribute(tokenKey, tokenValue);
                Cookie cookies = new Cookie(tokenKey, tokenValue);
                cookies.setPath("/");
                cookies.setMaxAge(900);
                response.addCookie(cookies);
                result.setState("true");
                result.setMessage("请求成功");
            }
            else{
                result.setState("false");
                result.setMessage("请求失败");
            }
        } catch (Exception e) {
            result.setState("false");
            result.setMessage("请求失败");
             e.printStackTrace();
        } finally {
            return result;
        }
    }
    /**
     * 分页显示已经报名的学生信息
     * @param pageNum 第几页
     * @param pageSize 一张页面显示几条信息
     * @return
     */
    @RequestMapping("/find")
    public @ResponseBody
    ResultList findParticipator(int pageNum, int pageSize){
        return managerService.findParticipatorByPage(pageNum,pageSize);
    }
}
