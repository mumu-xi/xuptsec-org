package org.xuptsec.recruit.poji;

/**
 * Created by mu on 2017/9/9.
 */
public class ResultJoin {
    private String state;
    private String message;

    public ResultJoin(String aFalse, String s) {
    }
    public ResultJoin() {
    }
    @Override
    public String toString() {
        return "Result{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
