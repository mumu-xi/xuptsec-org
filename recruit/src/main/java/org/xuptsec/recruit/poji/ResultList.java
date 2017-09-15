package org.xuptsec.recruit.poji;

import java.util.List;

/**
 * Created by mu on 2017/9/9.
 */
public class ResultList<T> {
    private String state;
    private String message;
    private int total;
    private T data;

    @Override
    public String toString() {
        return "ResultList{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
                ", total=" + total +
                ", data=" + data +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
