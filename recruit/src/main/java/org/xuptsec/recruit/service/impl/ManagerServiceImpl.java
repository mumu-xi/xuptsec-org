package org.xuptsec.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xuptsec.recruit.dao.ManagerMapper;
import org.xuptsec.recruit.poji.Manager;
import org.xuptsec.recruit.poji.ResultLogin;
import org.xuptsec.recruit.service.ManagerService;

/**
 * Created by mu on 2017/9/10.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    /**
     * 管理人员登录
     * @param username
     * @param password
     * @return
     */
    public ResultLogin managerLogin(String username, String password) {
        ResultLogin result = new ResultLogin();
        try {
            Manager manager = managerMapper.managerLogin(username, password);
            if(manager!=null)
            {
                result.setState("true");
                result.setMessage("请求成功");
            }
            else{
                result.setState("false");
                result.setMessage("请求失败");
            }
        } catch (Exception e) {


           // e.printStackTrace();
        } finally {
            return result;
        }
    }
}
