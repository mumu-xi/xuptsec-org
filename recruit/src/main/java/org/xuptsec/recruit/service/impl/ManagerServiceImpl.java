package org.xuptsec.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xuptsec.recruit.dao.ManagerMapper;
import org.xuptsec.recruit.dao.ParticipatorMapper;
import org.xuptsec.recruit.poji.Manager;
import org.xuptsec.recruit.poji.ResultList;
import org.xuptsec.recruit.poji.ResultPicture;
import org.xuptsec.recruit.service.ManagerService;
import org.xuptsec.recruit.utils.FastDFSUtils;

/**
 * Created by mu on 2017/9/10.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Value("${IMAGER_SERVER_BASE_URL}")
    private String IMAGER_SERVER_BASE_URL;
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
    /**
     * 上传图片，FastFDS分布式文件管理
     * @param picFile
     * @return
     */
    public ResultPicture uploadPic(MultipartFile picFile) {
        ResultPicture resultPicture = new ResultPicture();
        if(picFile.isEmpty()){
            resultPicture.setMessage("图片为空");
            resultPicture.setState("false");
            return resultPicture;
        }
        try {
            FastDFSUtils fastDFSUtils = new FastDFSUtils("classpath:fast.properties");
            String originalFilename = picFile.getOriginalFilename();
            String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            String picUrl =IMAGER_SERVER_BASE_URL+ fastDFSUtils.uploadFile(picFile.getBytes(), extName, null);
            System.out.println(picUrl);

            /*将picUrl地址存入数据库*/
            //.........以后加吧

            resultPicture.setPicUrl(picUrl);
            resultPicture.setMessage("图片上传成功");
            resultPicture.setState("true");
        } catch (Exception e) {
            resultPicture.setMessage("图片上传失败");
            resultPicture.setState("false");
            e.printStackTrace();
        }
        return resultPicture;
    }


}
