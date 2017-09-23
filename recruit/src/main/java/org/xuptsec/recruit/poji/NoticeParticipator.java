package org.xuptsec.recruit.poji;

/**
 * Created by mu on 2017/9/23.
 */
public class NoticeParticipator {
    private String id;
    private String stuName;
    private String stuSex;
    private String stuClass;
    private String stuGroup;
    private String schedule;

    @Override
    public String toString() {
        return "NoticeParticipator{" +
                "id='" + id + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", stuGroup='" + stuGroup + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStuGroup() {
        return stuGroup;
    }

    public void setStuGroup(String stuGroup) {
        this.stuGroup = stuGroup;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
