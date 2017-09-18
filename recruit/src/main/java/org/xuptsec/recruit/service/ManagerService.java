package org.xuptsec.recruit.service;

import org.xuptsec.recruit.poji.Manager;
import org.xuptsec.recruit.poji.ResultList;

/**
 * Created by mu on 2017/9/10.
 */
public interface ManagerService {
    Manager managerLogin(String username, String password);
    /**
     * 查找所有学生报名信息
     *
     * @return
     */
    ResultList findParticipatorByPage(int pageNum, int pageSize);

    /**
     * 根据账户名查询手机号
     * @param username
     * @return
     */
    String findManagerTelByUsername(String username);

}
