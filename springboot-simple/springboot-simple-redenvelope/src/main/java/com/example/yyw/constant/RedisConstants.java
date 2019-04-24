package com.example.yyw.constant;

import java.util.concurrent.TimeUnit;

/**
 * @author yanzt
 * @date 2019/4/25 0:02
 * @describe
 */
public class RedisConstants {

    public static final Long WAITTIME = 30L;
    public static final Long LEASETIME = 60L;
    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    public static final String INITQUN = "initQun_";
    public static final String INITUSER = "initUser_";
}
