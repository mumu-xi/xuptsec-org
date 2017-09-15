package org.xuptsec.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xuptsec.recruit.dao.ManagerMapper;
import org.xuptsec.recruit.dao.ParticipatorMapper;
import org.xuptsec.recruit.poji.Manager;
import org.xuptsec.recruit.poji.ResultList;
import org.xuptsec.recruit.service.ManagerService;

/**
 * Created by mu on 2017/9/10.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private ParticipatorMapper participatorMapper;
    /**
     * 管理人员登录
     * @param username
     * @param password
     * @return
     */
    public Manager managerLogin(String username, String password) {
        return managerMapper.managerLogin(username,password);
    }
    /**
     * 查找所有学生报名信息
     *
     * @return
     */
    public ResultList findParticipatorByPage(int pageNum, int pageSize) {
        ResultList result = new ResultList();
        try {
            result.setData(participatorMapper.findParticipatorByPage((pageNum - 1) * pageSize, pageSize));
            result.setTotal(participatorMapper.findTotalParticipator());
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
     * 根据账户名查询手机号
     * @param username
     * @return
     */
    public String findManagerTelByUsername(String username) {
        return managerMapper.findManagerTelByUsername(username);
    }
}
