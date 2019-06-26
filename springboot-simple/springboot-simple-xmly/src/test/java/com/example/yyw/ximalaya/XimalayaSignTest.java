package com.example.yyw.ximalaya;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/6/20 14:48
 * @describe
 */
@Slf4j
public class XimalayaSignTest {

    @Test
    public void test_create_album_sign(){
        String nonce = "ae30cfc5cc644b1da00111f70be8d5ed";
        Integer push_type = 1;
        String sig = "189eb88bd3feb9c6a19fdd314434601f";
        Long updated_at = 1561013029000L;
        Integer id = 16513734;
        Boolean is_online = true;
        Integer offline_reason_type = 1;
        Long timestamp = 1561013033321L;

        Map<String, Object> params = buildParams(APP_KEY, push_type, id, null, null, updated_at, is_online, offline_reason_type, nonce, timestamp);
        String sign = verifySign(params);
        log.info("sign==={}",sign);
    }
    @Test
    public void test_create_track_sign(){
        Integer subordinated_album_id = 16513734;
        String nonce = "ae30cfc5cc644b1da00111f70be8d5ed";
        Integer push_type = 2;
        String sig = "189eb88bd3feb9c6a19fdd314434601f";
        Long updated_at = 1561013029000L;
        Integer id = 100078235;
        Boolean is_online = true;
        Integer offline_reason_type = 1;
        Long timestamp = 1561013033321L;

        Map<String, Object> params = buildParams(APP_KEY, push_type, id, subordinated_album_id, null, updated_at, is_online, offline_reason_type, nonce, timestamp);
        String sign = verifySign(params);
        log.info("sign==={}",sign);
    }

    @Test
    public void test_ximalaya_sign() {
        Integer subordinated_album_id = 12228514;
        String nonce = "ae30cfc5cc644b1da00111f70be8d5ed";
        Integer push_type = 2;
        String sig = "189eb88bd3feb9c6a19fdd314434601f";
        Long updated_at = 1561013029000L;
        Integer id = 178118359;
        Boolean is_online = false;
        Integer offline_reason_type = 1;
        Long timestamp = 1561013033321L;

        Map<String, Object> params = buildParams(APP_KEY, push_type, id, subordinated_album_id, null, updated_at, is_online, offline_reason_type, nonce, timestamp);
        String sign = verifySign(params);

    }

    public Map<String, Object> buildParams(String app_key, Integer push_type, Integer id, Integer subordinated_album_id, Boolean is_paid,
                                           Long updated_at, Boolean is_online, Integer offline_reason_type, String nonce, Long timestamp) {
        Map<String, Object> params = new HashMap<>();
        params.put("app_key", app_key);
        params.put("push_type", push_type);
        params.put("id", id);
        params.put("subordinated_album_id", subordinated_album_id);
        params.put("is_paid", is_paid);
        params.put("updated_at", updated_at);
        params.put("is_online", is_online);
        params.put("offline_reason_type", offline_reason_type);
        params.put("nonce", nonce);
        params.put("timestamp", timestamp);
        return params;
    }

    private static final String APP_KEY = "ba39f971bcb35d12afe892ab20be0e14";
    private static final String APP_SECRET = "4749ba4fd2931e96744688aa1714b0cd";

    /**
     * 对参数进行校验
     *
     * @param params 包含sig
     * @return
     */
    public String verifySign(Map<String, Object> params) {
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuffer sign_param = new StringBuffer();

        for (String key : keys) {
            Object o = params.get(key);
            if (o != null) {
                sign_param.append(key).append("=").append(o).append("&");
            }
        }
        sign_param.append("app_secret=").append(APP_SECRET);
        String sign = DigestUtils.md5DigestAsHex(sign_param.toString().getBytes());
        log.info("sign_params : {},sign : {}", sign_param.toString(), sign);
        return sign;
    }
}
