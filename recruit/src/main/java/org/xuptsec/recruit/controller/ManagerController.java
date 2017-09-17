package org.xuptsec.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xuptsec.recruit.poji.*;
import org.xuptsec.recruit.service.ManagerService;
import org.xuptsec.recruit.utils.BASE64Util;
import org.xuptsec.recruit.utils.MD5Util;
import org.xuptsec.recruit.utils.SendMessageUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Random;
import java.util.UUID;

/**
 * Created by mu on 2017/9/10.
 */
@Controller
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    /**
     * 根据账号从数据库获取手机号，验证码
     *
     * @param session
     * @param username
     * @return
     */
    @RequestMapping(value="/verification",method = RequestMethod.POST)
    public @ResponseBody
    ResultLogin getCodeValue( HttpSession session,HttpServletResponse response, String username) {
        ResultLogin resultLogin = new ResultLogin("获取验证码失败！", "false");
        try {
            String stuTel = managerService.findManagerTelByUsername(username);
            if (stuTel != "" || stuTel != null) {
                //生成验证码
                String verification = String.valueOf(new Random().nextInt(89999) + 10000);
                SendMessageUtil.sendMessage(stuTel, verification);
                String key=MD5Util.md5("code" + stuTel);
                session.setAttribute(key, verification);


                String sessionID = session.getId();
                // Session的ID名称为JSESSIONID，可以通过HTTPWatch查看
                Cookie cookie = new Cookie("JSESSIONID", sessionID);
                cookie.setPath("/");  // 设置cookie的路径
                cookie.setMaxAge(30 * 60); // 设置的有效时间长度是session的存在时间 半个小时
                response.addCookie(cookie);


                System.out.println("验证码 sessionId= "+session.getId());
                String codeValue = (String) session.getAttribute(key);
                resultLogin.setMessage("获取成功！");
                resultLogin.setState("true");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultLogin;
    }

    /**
     * 管理员登录
     *
     * @param response
     * @param login
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResultLogin managerLogin(HttpServletResponse response, HttpServletRequest request,/* @RequestBody*/ @Valid Login login) {
        ResultLogin result = new ResultLogin("请求失败", "false");
        try {
            HttpSession session = request.getSession();
            Manager manager = managerService.managerLogin(login.getUsername(), login.getPassword());
            String stuTel = managerService.findManagerTelByUsername(login.getUsername());

            String key=MD5Util.md5("code" + stuTel);
            System.out.println("key: "+key);
            String codeValue = (String) session.getAttribute(key);
            System.out.println("登录 sessionId= "+session.getId());
            System.out.println(login.getVerification()+","+codeValue);
            if (codeValue != null && codeValue.equals(login.getVerification())) {
                session.removeAttribute("code" + stuTel);
                if (manager != null) {
                    session.setAttribute("manager", manager);
                    session.setMaxInactiveInterval(900);
                    String tokenKey = MD5Util.md5(manager.getUsername() + manager.getPassword() + BASE64Util.toBASE64(manager.getSalt()));
                    String tokenValue = BASE64Util.toBASE64(manager.getUsername() + UUID.randomUUID() + manager.getPassword() + MD5Util.md5(System.currentTimeMillis() + ""));
                    tokenValue = tokenValue.replaceAll("\r|\n", "");
                    session.setAttribute(tokenKey, tokenValue);
                    Cookie cookies = new Cookie(tokenKey, tokenValue);
                    cookies.setPath("/");
                    cookies.setMaxAge(900);
                    response.addCookie(cookies);
                    result.setState("true");
                    result.setMessage("登录成功");
                }
                else{
                    result.setState("false");
                    result.setMessage("账号或密码错误！");
                }
            }
            else{
                result.setState("false");
                result.setMessage("手机验证码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * 分页显示已经报名的学生信息
     *
     * @param pageNum  第几页
     * @param pageSize 一张页面显示几条信息
     * @return
     */
    @RequestMapping("/find")
    public @ResponseBody
    ResultList findParticipator(@RequestParam(value="pageNum",required = false,defaultValue = "1") int pageNum,@RequestParam(value="pageSize",required =false,defaultValue = "10") int pageSize) {

        return managerService.findParticipatorByPage(pageNum, pageSize);
    }

}