package org.xuptsec.recruit.controller;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
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
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        String path = request.getServletContext().getRealPath("/exam/二面试题.rar");
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String("二面试题.rar".getBytes("UTF-8"),"iso-8859-1");
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
     * 下载报名人部分表格（给报名者看）
     * @param response
     * @throws IOException
     */
    @RequestMapping("/Info")
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
        cell.setCellValue("专业班级");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("选择组别");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("面试时间");
        cell.setCellStyle(style);


        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<Participator> list = participatorService.findParticipatorAll();

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);

            Participator par = list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell(0).setCellValue(par.getStuName());
            row.createCell(1).setCellValue(par.getStuClass());
            row.createCell(2).setCellValue(par.getStuGroup());
            row.createCell(3).setCellValue(par.getSchedule());
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
