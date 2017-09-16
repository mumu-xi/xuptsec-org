package org.xuptsec.recruit.poji;

/**
 * Created by mu on 2017/9/16.
 */
public class Member {
    private String peopleId;
    private String peopleName;
    private String peopleSex;
    private String peopleClass;
    private String peopleGroup;
    private String peopleIntro;
    private String picUrl;

    @Override
    public String toString() {
        return "Member{" +
                "peopleId='" + peopleId + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", peopleSex='" + peopleSex + '\'' +
                ", peopleClass='" + peopleClass + '\'' +
                ", peopleGroup='" + peopleGroup + '\'' +
                ", peopleIntro='" + peopleIntro + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

    public String getpeopleGroup() {
        return peopleGroup;
    }

    public void setpeopleGroup(String peopleGroup) {
        this.peopleGroup = peopleGroup;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getpeopleId() {
        return peopleId;
    }

    public void setpeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getpeopleClass() {
        return peopleClass;
    }

    public void setpeopleClass(String peopleClass) {
        this.peopleClass = peopleClass;
    }

    public String getpeopleName() {
        return peopleName;
    }

    public void setpeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getpeopleSex() {
        return peopleSex;
    }

    public void setpeopleSex(String peopleSex) {
        this.peopleSex = peopleSex;
    }

    public String getpeopleIntro() {
        return peopleIntro;
    }

    public void setpeopleIntro(String peopleIntro) {
        this.peopleIntro = peopleIntro;
    }

    public String getPicurl() {
        return picUrl;
    }

    public void setPicurl(String picurl) {
        this.picUrl = picurl;
    }
}
