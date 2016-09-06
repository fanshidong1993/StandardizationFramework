package com.sdf.www.standardizationframework.bean;

import java.io.Serializable;

/**
 * Created by shadow on 16/8/22.
 */

public class DemoBean implements Serializable {
    String nickname;
    String bakName;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBakName() {
        return bakName;
    }

    public void setBakName(String bakName) {
        this.bakName = bakName;
    }
}
