package com.xq.crowd_funding.login.bean.pojo;

import lombok.Data;

@Data
public class UserToken {
    private long id;
    private String loginacct;
    private String userpswd;
    private String usertoken;
    private String raiseToken;
}
