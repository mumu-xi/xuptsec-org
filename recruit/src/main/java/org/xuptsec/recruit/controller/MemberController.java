package org.xuptsec.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xuptsec.recruit.poji.ResultList;
import org.xuptsec.recruit.service.MemberService;

/**
 * Created by mu on 2017/9/16.
 */
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
    public @ResponseBody ResultList findLabMemberByPage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        return memberService.findLabMemberByPage(pageNum,pageSize);
    }

}
