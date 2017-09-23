package org.xuptsec.recruit.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xuptsec.recruit.poji.Participator;
import org.xuptsec.recruit.poji.ResultJoin;
import org.xuptsec.recruit.poji.ResultList;
import org.xuptsec.recruit.service.ParticipatorService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;

/**
 * Created by mu on 2017/9/9.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/participator")
public class ParticipatorController {
    @Autowired
    private ParticipatorService participatorService;

    /**
     * 获取学生填写的报名信息
     * @param participator
     * @return result状态码  "message":"请求成功","state":true
     */
    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public @ResponseBody
    ResultJoin insertParticipator(@RequestBody  @Valid Participator participator,BindingResult result){
        if(result.hasErrors()){
            return new ResultJoin("false","表单验证失败" );
        }
        participator.setStuIntro(participator.getStuIntro().replace(">","？").replace("/","？").replace("'","？").replace("\"","？").replace("cript","？"));
        return participatorService.insertParticipator(participator);
    }

    /**
     * 通知报名者面试时间，查询部分信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/schedule")
    public @ResponseBody
    ResultList findNoticeParticipator(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return participatorService.findNoticeParticipator(pageNum, pageSize);
    }
    /**
     * 下载安全组免试题
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/freeQR")
    public ResponseEntity<byte[]> freeQR(HttpServletRequest request,
                                           Model model)throws Exception {
        //下载文件路径
        String path = request.getServletContext().getRealPath("/exam/nikeyide.png");
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String("nikeyide.png".getBytes("UTF-8"),"iso-8859-1");
        File file = new File(path );
        HttpHeaders headers = new HttpHeaders();

        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    /**
     *下载面试题
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/interview")
    public ResponseEntity<byte[]> interview(HttpServletRequest request,
                                           Model model)throws Exception {
        //下载文件路径
        String path = request.getServletContext().getRealPath("/exam/信安实验室笔试题.rar");
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String("信安实验室笔试题.rar".getBytes("UTF-8"),"iso-8859-1");
        File file = new File(path );
        HttpHeaders headers = new HttpHeaders();

        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

}
