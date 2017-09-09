package org.xuptsec.recruit.poji;

/**
 * Created by mu on 2017/9/9.
 */
public class Participator {
    private String stuId;
    private String stuName;
    /**
     * 为了以后微信接入方便，获取表单时的 1 代表男，2 代表女，逻辑层转换为汉字
     */
    private String stuSex;
    private String stuClass;
    private String stuNumber;
    private String stuTel;
    private String stuGroup;
    private String stuIntro;

    @Override
    public String toString() {
        return "Participator{" +
                "stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", stuTel='" + stuTel + '\'' +
                ", stuGroup='" + stuGroup + '\'' +
                ", stuIntro='" + stuIntro + '\'' +
                '}';
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getStuTel() {
        return stuTel;
    }

    public void setStuTel(String stuTel) {
        this.stuTel = stuTel;
    }

    public String getStuGroup() {
        return stuGroup;
    }

    public void setStuGroup(String stuGroup) {
        this.stuGroup = stuGroup;
    }

    public String getStuIntro() {
        return stuIntro;
    }

    public void setStuIntro(String stuIntro) {
        this.stuIntro = stuIntro;
    }
}
