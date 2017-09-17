package org.xuptsec.recruit.utils;

import java.io.*;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class CreateXLS {
    public static void main(String[] args) throws IOException, WriteException {
        FileOutputStream fos=new FileOutputStream(new File("C:\\Users\\mu\\Desktop\\1.xls"));
        createExcel(fos);
    }
    public static void createExcel(OutputStream os) throws WriteException,IOException{
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label stuName = new Label(0,0,"姓名");
        sheet.addCell(stuName);
        Label stuSex = new Label(1,0,"性别");
        sheet.addCell(stuSex);
        Label stuClass = new Label(2,0,"专业班级");
        sheet.addCell(stuClass);
        Label stuTel = new Label(3,0,"电话号码");
        sheet.addCell(stuTel);
        Label stuGroup = new Label(4,0,"选择组别");
        sheet.addCell(stuGroup);

        Label qinghua = new Label(0,1,"清华大学");
        sheet.addCell(qinghua);
        Label jisuanji = new Label(1,1,"计算机专业");
        sheet.addCell(jisuanji);
        Label gao = new Label(2,1,"高");
        sheet.addCell(gao);

        Label beida = new Label(0,2,"北京大学");
        sheet.addCell(beida);
        Label falv = new Label(1,2,"法律专业");
        sheet.addCell(falv);
        Label zhong = new Label(2,2,"中");
        sheet.addCell(zhong);

        Label ligong = new Label(0,3,"北京理工大学");
        sheet.addCell(ligong);
        Label hangkong = new Label(1,3,"航空专业");
        sheet.addCell(hangkong);
        Label di = new Label(2,3,"低");
        sheet.addCell(di);

        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }


}