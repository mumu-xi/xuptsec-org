package org.xuptsec.recruit.poji;

import java.util.List;

/**
 * Created by mu on 2017/9/9.
 */
public class ResultList<T> {
    private String state;
    private String message;
    private T data;

    @Override
    public String toString() {
        return "ResultStudentList{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
