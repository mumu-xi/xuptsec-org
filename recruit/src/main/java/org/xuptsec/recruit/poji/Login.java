package org.xuptsec.recruit.poji;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by mu on 2017/9/12.
 */
public class Login {
    @NotEmpty()
    @Size(max=20)
    @Pattern(regexp = "\\w+")
    private String username;
    @NotEmpty()
    @Size(max=20)
    @Pattern(regexp = "\\w+")
    private String password;
   /* @NotEmpty()
   @Pattern(regexp = "\\d+")
    @Size(max=10)*/

    private String verification;

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", verification='" + verification + '\'' +
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

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
