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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    @RequestMapping("/verification")
    public @ResponseBody
    ResultLogin getCodeValue(HttpSession session, String username) {
        ResultLogin resultLogin = new ResultLogin("获取验证码失败！", "false");
        try {
            String stuTel = managerService.findManagerTelByUsername(username);
            if (stuTel != "" || stuTel != null) {
                //生成验证码
                String verification = String.valueOf(new Random().nextInt(89999) + 10000);
                SendMessageUtil.sendMessage(stuTel, verification);
                session.setAttribute(MD5Util.md5("code" + stuTel), verification);
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
     * @param session
     * @param login
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResultLogin managerLogin(HttpServletResponse response, HttpSession session, @RequestBody Login login) {
        ResultLogin result = new ResultLogin("请求失败", "false");
        try {
            Manager manager = managerService.managerLogin(login.getUsername(), login.getPassword());
            String stuTel = managerService.findManagerTelByUsername(login.getUsername());
            String codeValue = (String) session.getAttribute(MD5Util.md5("code" + stuTel));
            if (codeValue != null && codeValue.equals(login.getVerification())) {
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
                    result.setMessage("请求成功");
                }
                else{
                    result.setState("false");
                    result.setMessage("账号密码错误！");
                }
            }
            else{
                result.setState("false");
                result.setMessage("验证码错误！");
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
    ResultList findParticipator(int pageNum, int pageSize) {
        return managerService.findParticipatorByPage(pageNum, pageSize);
    }

}
