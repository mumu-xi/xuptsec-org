package org.xuptsec.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xuptsec.recruit.poji.Participator;
import org.xuptsec.recruit.poji.ResultJoin;
import org.xuptsec.recruit.poji.ResultStudentList;
import org.xuptsec.recruit.service.ParticipatorService;

/**
 * Created by mu on 2017/9/9.
 */
@Controller
@RequestMapping("/api/participator")
public class JoinStudentController {
    @Autowired
    private ParticipatorService participatorService;

    /**
     * 获取学生填写的报名信息
     * @param participator
     * @return result状态码  "message":"请求成功","state":true
     */
    @RequestMapping(value="/insert"/*,method = RequestMethod.POST*/)
    public @ResponseBody
    ResultJoin insertParticipator(Participator participator){
        return participatorService.insertParticipator(participator);
    }

    /**
     * 分页显示已经报名的学生信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/find")
    public @ResponseBody ResultStudentList findParticipator(String pageNum,String pageSize){
        return participatorService.findParticipatorByPage(pageNum,pageSize);
    }
}
