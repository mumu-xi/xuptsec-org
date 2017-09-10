package org.xuptsec.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xuptsec.recruit.poji.ResultLogin;
import org.xuptsec.recruit.service.ManagerService;

/**
 * Created by mu on 2017/9/10.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public @ResponseBody ResultLogin managerLogin(String username, String password){
        return  managerService.managerLogin(username, password);
    }
}
