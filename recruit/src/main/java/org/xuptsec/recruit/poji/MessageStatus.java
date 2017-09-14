package org.xuptsec.recruit.poji;

/**
 * Created by mu on 2017/9/13.
 */
public class MessageStatus {
    private String Code;
    private String Message;
    private String RequestId;
    private String BizId;

    private String verification;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getBizId() {
        return BizId;
    }

    public void setBizId(String bizId) {
        BizId = bizId;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    @Override
    public String toString() {
        return "MessageStatus{" +
                "Code='" + Code + '\'' +
                ", Message='" + Message + '\'' +
                ", RequestId='" + RequestId + '\'' +
                ", BizId='" + BizId + '\'' +
                ", verification='" + verification + '\'' +
                '}';
    }
}
