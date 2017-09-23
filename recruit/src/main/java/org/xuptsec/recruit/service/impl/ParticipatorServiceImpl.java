package org.xuptsec.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xuptsec.recruit.dao.ParticipatorMapper;
import org.xuptsec.recruit.poji.Participator;
import org.xuptsec.recruit.poji.ResultJoin;
import org.xuptsec.recruit.poji.ResultList;
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
        try {

            int count = participatorMapper.findParticipatorByStuNum(participator.getStuNumber(),participator.getStuName());
            if(count>0){
                result.setMessage("你的热情我们可以理解，请不要重复报名。");
            }
           else if(count == 0) {
            /*
             * 为了以后微信接入方便，获取表单时的 1 代表男，2 代表女，在此处转换为汉字
             */
                String sex = participator.getStuSex().trim();
                if ((sex == "1") || (sex == "2"))
                    participator.setStuSex("1".equals(sex) ? "男" : "女");


                participatorMapper.insertParticipator(participator);
                result.setMessage("报名成功");
            }


            result.setState("true");
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
     *
     * @return
     */
    public List<Participator> findParticipatorAll() {
        return participatorMapper.findParticipatorAll();
    }

    /**
     * 通知报名者面试时间，查询部分信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ResultList findNoticeParticipator(int pageNum, int pageSize) {
        ResultList result = new ResultList();
        try {
            result.setData(participatorMapper.findNoticeParticipator((pageNum - 1) * pageSize, pageSize));
            result.setTotal(participatorMapper.findTotalParticipator());
            result.setState("true");
            result.setMessage("请求成功");
        } catch (Exception e) {
            result.setState("false");
            result.setMessage("请求失败");
            e.printStackTrace();
        }
        return result;
    }


}
