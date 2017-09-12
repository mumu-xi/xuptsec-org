package org.xuptsec.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xuptsec.recruit.poji.Manager;
import org.xuptsec.recruit.poji.ResultLogin;
import org.xuptsec.recruit.service.ManagerService;

import javax.servlet.http.HttpSession;

/**
 * Created by mu on 2017/9/10.
 */
@Controller
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public @ResponseBody ResultLogin managerLogin(HttpSession  session,String username, String password){
        ResultLogin result = new ResultLogin();
        try {
            Manager manager = managerService.managerLogin(username, password);
            if(manager!=null){
                session.setAttribute("manager",manager);
                session.setMaxInactiveInterval(900);

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
}
