package org.xuptsec.recruit.poji;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * Created by mu on 2017/9/9.
 */
public class Participator implements Serializable {
    private String stuId;
    @Size(min = 2,max=10)
    @Pattern(regexp = "[\\u4e00-\\u9fa5]+")
    @NotEmpty()
    private String stuName;

    @NotEmpty
    @Size(max=4)
    @Pattern(regexp = "[\\u4e00-\\u9fa5]+")
    private String stuSex;
    @NotEmpty
    @Size(max=30)
    @Pattern(regexp = "[\\d\\u4e00-\\u9fa5]+")
    private String stuClass;
    @NotEmpty
    @Pattern(regexp = "\\d{6,10}")
    private String stuNumber;
    @Pattern(regexp = "\\d{1,20}")
    @NotEmpty
    private String stuTel;
    @NotEmpty
    @Size(max=10)
    @Pattern(regexp = "[\\u4e00-\\u9fa5]*")
    private String stuGroup;
    @Size(max=512)
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
