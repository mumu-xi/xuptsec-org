package org.xuptsec.recruit.poji;

/**
 * Created by mu on 2017/9/10.
 */
public class ResultLogin  {
    private String state;
    private String message;

    public ResultLogin(String message,String state) {
        this.state = state;
        this.message = message;
    }

    public ResultLogin() {
    }

    @Override
    public String toString() {
        return "ResultLogin{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
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
}
