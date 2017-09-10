package org.xuptsec.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xuptsec.recruit.dao.ParticipatorMapper;
import org.xuptsec.recruit.poji.Participator;
import org.xuptsec.recruit.poji.ResultJoin;
import org.xuptsec.recruit.poji.ResultStudentList;
import org.xuptsec.recruit.service.ParticipatorService;

/**
 * Created by mu on 2017/9/9.
 */
@Service
public class ParticipatorServiceImpl implements ParticipatorService {
    @Autowired
    private ParticipatorMapper participatorMapper;
    /**
     * 插入学生报名信息
     * @param participator
     * @return
     */
    public ResultJoin insertParticipator(Participator participator) {
        ResultJoin result = new ResultJoin();
        try {
            /*
             * 为了以后微信接入方便，获取表单时的 1 代表男，2 代表女，在此处转换为汉字
             */
            participator.setStuSex("1".equals(participator.getStuSex().trim())?"男":"女");
            participatorMapper.insertParticipator(participator);
            result.setState("true");
            result.setMessage("请求成功");
        } catch (Exception e) {
            result.setState("false");
            result.setMessage("请求失败");

            e.printStackTrace();
        } finally {
            return result;
        }

    }
    /**
     * 查找所有学生报名信息
     * @return
     */
    public ResultStudentList findParticipatorByPage(int pageNum,int pageSize){
        ResultStudentList result = new ResultStudentList();
        try {


            result.setData(participatorMapper.findParticipatorByPage((pageNum-1)*pageSize,pageSize));
            result.setState("true");
            result.setMessage("请求成功");
        } catch (Exception e) {
            result.setState("false");
            result.setMessage("请求失败");

            e.printStackTrace();
        } finally {
            return result;
        }


    }
}
