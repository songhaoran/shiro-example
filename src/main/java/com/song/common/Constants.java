package com.song.common;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class Constants {
    public static final String CURRENT_USER = "user";

    /**
     * 当用户被踢出登陆时,设置到该用户session中的attribute名称,值设置为sessionId
     */
    public static final String KICK_OUT_SESSION = "kick_out_session";

    /**
     * 每个账户可以同时登陆的终端数
     */
    public static final Integer EVERY_ACCOUNT_CURRENT_LOGINED_NUM = 2;
}
