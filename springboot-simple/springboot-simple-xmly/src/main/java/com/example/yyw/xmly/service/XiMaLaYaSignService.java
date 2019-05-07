package com.example.yyw.xmly.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 喜马拉雅签名服务
 *
 * @author yanzhitao
 **/
@Service
public class XiMaLaYaSignService{
    private static final String APP_KEY = "ba39f971bcb35d12afe892ab20be0e14";
    private static final String APP_SECRET = "4749ba4fd2931e96744688aa1714b0cd";
    private static final String SERVER_AUTHENTICATE_STATIC_KEY = "hkol6TPy";
    private static final String CLIENT_OS_TYPE_SERVER_API = "4";
    private static final String SERVER_API_VERSION = "1.0.0";

    private static Map<String, String> fetchCommonParams(){
        return new HashMap<String, String>(){{
            put("app_key", APP_KEY);
            put("client_os_type", CLIENT_OS_TYPE_SERVER_API);
            put("nonce", UUID.randomUUID().toString());
            put("timestamp", System.currentTimeMillis() + "");
            put("server_api_version", SERVER_API_VERSION);
        }};
    }

    /**
     * 构建最终的参数url（默认使用默认的公共参数键值对，不可覆写）
     *
     * @param params 特殊参数键值对
     * @return 参数url
     */
    public static String buildParamUrl(Map<String, String> params){
        return buildParamUrl(params, false);
    }

    /**
     * 构建最终的参数url
     *
     * @param params                  特殊参数键值对
     * @param isOverwriteCommonParams 是否要用特殊参数键值对，覆写公共参数键值对
     * @return 参数url
     */
    public static String buildParamUrl(Map<String, String> params, boolean isOverwriteCommonParams){
        Map<String, String> allParams = buildParams(params, isOverwriteCommonParams);
        allParams.entrySet().forEach(entry -> {
            try{
                entry.setValue(URLEncoder.encode(entry.getValue(), "utf-8"));
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
        });
        return buildUrl(allParams);
    }

    /**
     * 计算签名值
     *
     * @param params 参数键值对
     * @return 签名值
     */
    protected static String calculateSig(Map<String, String> params){
        String base64EncodedStr = buildBase64EncodedStr(params);
        String sha1Key = APP_SECRET + SERVER_AUTHENTICATE_STATIC_KEY;
        byte[] sha1ResultBytes = HmacUtils.hmacSha1(sha1Key, base64EncodedStr);
        return DigestUtils.md5Hex(sha1ResultBytes);
    }

    /**
     * 将特殊参数和公共参数进行组合，获取最终参数键值对
     *
     * @param params                  特殊参数键值对
     * @param isOverwriteCommonParams 是否可以用特殊键值对覆写公共参数键值对
     * @return 所有参数键值对
     */
    public static Map<String, String> buildParams(Map<String, String> params, boolean isOverwriteCommonParams){
        Map<String, String> commonParams = fetchCommonParams();
        if(isOverwriteCommonParams){
            commonParams.putAll(params);
        }else{
            for(Map.Entry<String, String> entry : params.entrySet()){
                commonParams.putIfAbsent(entry.getKey(), entry.getValue());
            }
        }
        String sig = calculateSig(commonParams);
        commonParams.put("sig", sig);
        return commonParams;
    }

    /**
     * 构建base64编码的字符串
     *
     * @param allParams 所有参数键值对
     * @return base编码字符串
     */
    protected static String buildBase64EncodedStr(Map<String, String> allParams){
        String[] keys = allParams.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder paramsUrlBuilder = new StringBuilder();
        for(String key : keys){
            paramsUrlBuilder.append(key + "=" + allParams.get(key) + "&");
        }
        String paramsUrl = StringUtils.substringBeforeLast(paramsUrlBuilder.toString(), "&");
        String base64EncodedStr = Base64.encodeBase64String(paramsUrl.getBytes(Charset.forName("utf-8")));
        return base64EncodedStr;
    }

    /**
     * 将参数键值对拼接成url参数字符串
     *
     * @param params
     * @return
     */
    private static String buildUrl(Map<String, String> params){
        StringBuilder paramsUrlBuilder = new StringBuilder();
        for(Map.Entry<String, String> entry : params.entrySet()){
            paramsUrlBuilder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return StringUtils.substringBeforeLast(paramsUrlBuilder.toString(), "&");
    }
}
