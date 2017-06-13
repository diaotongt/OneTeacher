package com.rucdm.oneteacher.oneteacher.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/12.
 */
public class LoginBean implements Serializable {
    private LoginData serverResult;

    public LoginData getServerResult() {
        return serverResult;
    }

    public void setServerResult(LoginData serverResult) {
        this.serverResult = serverResult;
    }

    public class LoginData {
        private int resultCode;
        private String resultMessage;

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMessage() {
            return resultMessage;
        }

        public void setResultMessage(String resultMessage) {
            this.resultMessage = resultMessage;
        }
    }
}
