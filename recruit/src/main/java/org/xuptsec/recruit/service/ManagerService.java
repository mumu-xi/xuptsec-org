package org.xuptsec.recruit.service;

import org.springframework.web.multipart.MultipartFile;
import org.xuptsec.recruit.poji.Manager;
import org.xuptsec.recruit.poji.ResultList;
import org.xuptsec.recruit.poji.ResultPicture;

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

    /**
     * 上传图片，FastFDS分布式文件管理
     * @param file
     * @return
     */
    ResultPicture uploadPic(MultipartFile file);
}
