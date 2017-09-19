package org.xuptsec.recruit.poji;

import java.util.Arrays;

/**
 * Created by mu on 2017/9/16.
 */
public class Member {
    private String peopleId;
    private String peopleName;
    private String str;
    private String[] peopleIntro;
    private String picUrl;

    @Override
    public String toString() {
        return "Member{" +
                "peopleId='" + peopleId + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", str='" + str + '\'' +
                ", peopleIntro=" + Arrays.toString(peopleIntro) +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String[] getPeopleIntro() {
        return peopleIntro;
    }

    public void setPeopleIntro(String[] peopleIntro) {
        this.peopleIntro = peopleIntro;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
