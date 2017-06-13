package com.rucdm.oneteacher.oneteacher.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/14.
 */
public class SbBean implements Serializable {
    private String loginName;
    private String password;
    private String packageName;

    public SbBean(String loginName, String password, String packageName) {
        this.loginName = loginName;
        this.password = password;
        this.packageName = packageName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
