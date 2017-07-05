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

    /**
     * 记录用户尝试登录次数的前缀
     */
    public static final String USER_TRY_LOGIN_TIMES_PRE = "user_try_login_times:";

    /**
     * 用户在规定时间内允许输错密码的次数
     */
    public static final Integer USER_CAN_WRONG_PASSWORD_TIMES = 5;

    /**
     * 当用户输入密码次数超出限制,放到用户request域中的标识字段
     */
    public static final String WRONG_PASSWORD_TOO_MANY_TIMES = "WRONG_PASSWORD_TOO_MANY_TIMES";
}
