package org.xuptsec.recruit.poji;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by mu on 2017/9/12.
 */
public class Login {
    @NotEmpty()
    private String username;
    @NotEmpty()
    private String password;
    @NotEmpty()
    private String codeValue;

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", codeValue='" + codeValue + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcodeValue() {
        return codeValue;
    }

    public void setcodeValue(String codeValue) {
        this.codeValue = codeValue;
    }
}
