package org.xuptsec.recruit.controller;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xuptsec.recruit.poji.*;
import org.xuptsec.recruit.service.ManagerService;
import org.xuptsec.recruit.service.ParticipatorService;
import org.xuptsec.recruit.utils.BASE64Util;
import org.xuptsec.recruit.utils.MD5Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by mu on 2017/9/10.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private ParticipatorService participatorService;

    /**
     * 根据账号从数据库获取手机号，验证码
     *
     * @param session
     * @param username
     * @return
     */
    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    public @ResponseBody
    ResultLogin getCodeValue(HttpSession session, HttpServletResponse response, String username) {
        ResultLogin resultLogin = new ResultLogin("获取验证码失败！", "false");
        try {
            String stuTel = managerService.findManagerTelByUsername(username);
            if (stuTel != "" || stuTel != null) {
                //生成验证码
                String verification = String.valueOf(new Random().nextInt(89999) + 10000);
              //  SendMessageUtil.sendMessage(stuTel, verification);
                String key = MD5Util.md5("code" + stuTel);
                //将验证码放入session 以便下次提交可以匹配验证码
                session.setAttribute(key, verification);


                String sessionID = session.getId();
                // Session的ID名称为JSESSIONID，可以通过HTTPWatch查看
                Cookie cookie = new Cookie("JSESSIONID", sessionID);
                cookie.setPath("/");  // 设置cookie的路径
                cookie.setMaxAge(30 * 60); // 设置的有效时间长度是session的存在时间 半个小时
                response.addCookie(cookie);


                System.out.println("验证码 sessionId= " + session.getId());
                String codeValue = (String) session.getAttribute(key);
                resultLogin.setMessage("获取成功！");
                resultLogin.setState("true");
            }
            else{
                resultLogin.setMessage("账号或手机号不存在");
                resultLogin.setState("false");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultLogin;
    }

    /**
     * 管理员登录
     *
     * @param response
     * @param login
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResultLogin managerLogin(HttpServletResponse response, HttpServletRequest request, /*@RequestBody*/ @Valid Login login,/*表单验证框架，不能删除此参数*/BindingResult result) {


        ResultLogin resultLogin = new ResultLogin("请求失败", "false");
        //表单验证
        if(result.hasErrors()){
            resultLogin.setMessage("表单验证失败");
            return resultLogin;
        }

        try {
            HttpSession session = request.getSession();
            Manager manager = managerService.managerLogin(login.getUsername(), login.getPassword());
            String stuTel = managerService.findManagerTelByUsername(login.getUsername());
            //生成验证码的session键
            String key = MD5Util.md5("code" + stuTel);
            System.out.println("key: " + key);
            //根据验证码session键取出验证码
            String codeValue = (String) session.getAttribute(key);
            System.out.println("登录 sessionId= " + session.getId());
            System.out.println(login.getVerification()+","+codeValue);
            //对比验证码是否一致
            if (codeValue != null && codeValue.equals(login.getVerification())) {
                //验证码一致后，从session中删除已验证过的验证码
            session.removeAttribute("code" + stuTel);
            //若根据账号密码从数据库能找到用户，则开始登陆
            if (manager != null) {
                session.setAttribute("manager", manager);
                session.setMaxInactiveInterval(900);
                //生成加密key，以便存入token
                String tokenKey = MD5Util.md5(manager.getUsername() + manager.getPassword() + BASE64Util.toBASE64(manager.getSalt()));
                String tokenValue = BASE64Util.toBASE64(manager.getUsername() + UUID.randomUUID() + manager.getPassword() + MD5Util.md5(System.currentTimeMillis() + ""));
                //当base64超过64个长度，会自动插入\r|\n，会导致抛出异常，所以替换其为空
                tokenValue = tokenValue.replaceAll("\r|\n", "");
                //将token放入session
                session.setAttribute(tokenKey, tokenValue);
                //将token放入cookies发送至客户端
                Cookie cookies = new Cookie("token", tokenValue);
                cookies.setPath("/");
                cookies.setMaxAge(900);
                response.addCookie(cookies);
                resultLogin.setState("true");
                resultLogin.setMessage("登录成功");
            } else {
                resultLogin.setState("false");
                resultLogin.setMessage("账号或密码错误！");
            }
           }
            else{
                resultLogin.setState("false");
                resultLogin.setMessage("手机验证码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultLogin;
    }

    /**
     * 分页显示已经报名的学生信息
     *
     * @param pageNum  第几页
     * @param pageSize 一张页面显示几条信息
     * @return
     */
    @RequestMapping("/find")
    public @ResponseBody
    ResultList findParticipator(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return managerService.findParticipatorByPage(pageNum, pageSize);
    }

    /**
     * 将数据库所有用户信息导出表格，下载报名人全部信息表格（给管理员看）
     * @param response
     * @throws IOException
     */
    @RequestMapping("/downloadInfo")
    public void createExcel(HttpServletResponse response) throws IOException {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("Sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        sheet.setDefaultRowHeightInPoints(12);//设置缺省列高
        sheet.setDefaultColumnWidth(15);//设置缺省列宽
        //设置指定列的列宽，256 * 50这种写法是因为width参数单位是单个字符的256分之一
        // sheet.setColumnWidth(cell.getColumnIndex(), 256 * 50);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("专业班级");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("学号");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("电话号码");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("选择组别");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("面试时间");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("个人介绍");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<Participator> list = participatorService.findParticipatorAll();

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);

            Participator par = list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell(0).setCellValue(par.getStuName());
            row.createCell(1).setCellValue(par.getStuSex());
            row.createCell(2).setCellValue(par.getStuClass());
            row.createCell(3).setCellValue(par.getStuNumber());
            row.createCell(4).setCellValue(par.getStuTel());
            row.createCell(5).setCellValue(par.getStuGroup());
            row.createCell(6).setCellValue(par.getSchedule());
            row.createCell(7).setCellValue(par.getStuIntro());
        }
        //第六步,输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        long filename = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String fileName = df.format(new Date());// new Date()为获取当前系统时间
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }




}
