package org.xuptsec.recruit.poji;

import java.util.Arrays;

/**
 * Created by mu on 2017/9/16.
 */
public class Member {

    private String peopleName;
    private String str;
    private String[] peopleIntro;

    @Override
    public String toString() {
        return "Member{" +
                "peopleName='" + peopleName + '\'' +
                ", str='" + str + '\'' +
                ", peopleIntro=" + Arrays.toString(peopleIntro) +
                '}';
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
}
