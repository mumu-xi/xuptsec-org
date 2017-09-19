package org.xuptsec.recruit.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xuptsec.recruit.service.MemberService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mu on 2017/9/16.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 分页查询实验室内部成员
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/find")
    public String findLabMemberByPage(HttpServletResponse response,@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        String json = JSONArray.toJSON(memberService.findLabMemberByPage(pageNum, pageSize)).toString();
        try {
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

}
