package org.xuptsec.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xuptsec.recruit.dao.ParticipatorMapper;
import org.xuptsec.recruit.poji.Participator;
import org.xuptsec.recruit.poji.ResultJoin;
import org.xuptsec.recruit.service.ParticipatorService;

import java.util.List;

/**
 * Created by mu on 2017/9/9.
 */
@Service
public class ParticipatorServiceImpl implements ParticipatorService {
    @Autowired
    private ParticipatorMapper participatorMapper;

    /**
     * 插入学生报名信息
     *
     * @param participator
     * @return
     */
    public ResultJoin insertParticipator(Participator participator) {
        ResultJoin result = new ResultJoin();
        StringBuilder sb = new StringBuilder();
        try {
            //表单验证
            if(participator==null){
                sb.append("请求参数为空");
            }
            if (participator.getStuName().trim() == "")
                sb.append("名字为空 ");
            if (participator.getStuClass().trim() == "")
                sb.append("班级为空 ");
            if (participator.getStuGroup().trim() == "")
                sb.append("选择组别为空 ");
            if (participator.getStuNumber().trim() == "")
                sb.append("学号为空 ");
            if (participator.getStuSex().trim() == "")
                sb.append("性别为空 ");
            if (participator.getStuTel().trim() == "")
                sb.append("电话为空 ");
            if (participator.getStuIntro().trim() == "")
                sb.append("个人介绍为空。");

            /*
             * 为了以后微信接入方便，获取表单时的 1 代表男，2 代表女，在此处转换为汉字
             */
            String sex = participator.getStuSex().trim();
            if ((sex == "1") || (sex == "2"))
                participator.setStuSex("1".equals(sex) ? "男" : "女");
            participatorMapper.insertParticipator(participator);
            result.setState("true");
            result.setMessage("报名成功");
        } catch (Exception e) {
            result.setState("false");
            result.setMessage("非常抱歉，报名失败了，请再试一次。");

            e.printStackTrace();
        } finally {

            return result;
        }
    }
    /**
     * 查找所有已报名学生的信息
     * @return
     */
    public List<Participator> findParticipatorAll() {
        return participatorMapper.findParticipatorAll();
    }


}
