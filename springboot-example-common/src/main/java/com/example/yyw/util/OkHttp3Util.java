package com.example.yyw.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/15 22:47
 */
@Slf4j
public class OkHttp3Util {

    private static final MediaType JSON_MEDIATYPE = MediaType.get("application/json; charset=utf-8");
    private static final MediaType FORMDATA_MEDIATYPE = MediaType.get("multipart/form-data; charset=utf-8");

    public static MediaType getJsonMediatype() {
        return JSON_MEDIATYPE;
    }

    private static final Long CONNECT_TIMEOUT = 10000L;
    private static final Long READ_TIMEOUT = 2000L;
    private static final Long WRITE_TIMEOUT = 2000L;
    private static final TimeUnit MILLISECONDS = TimeUnit.MILLISECONDS;

    @NotNull
    private static OkHttpClient initOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, MILLISECONDS)
                .readTimeout(READ_TIMEOUT, MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, MILLISECONDS)
                .build();
    }

    public static String get(String url) {
        try {
            OkHttpClient client = initOkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String result = Objects.requireNonNull(response.body()).string();
                log.info("OkHttp3Util get success。[URL: {}],[response: {}]", url, result);
                return result;
            }
        } catch (Exception e) {
            log.error("OkHttp3Util get error : {}", e.getMessage());
            return "";
        }
    }

    public static String post(String url, String json, MediaType mediaType) {
        if(mediaType == null) {
            mediaType = JSON_MEDIATYPE;
        }
        try {
            OkHttpClient client = initOkHttpClient();

            RequestBody requestBody = RequestBody.create(json, mediaType);

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                StringBuffer result = new StringBuffer();
                Optional.ofNullable(response.body()).ifPresent(responseBody -> {
                    try {
                        result.append(responseBody.string());
                    } catch (IOException e) {
                        log.error("OkHttp3Util post responseBody.string error : {}", e.getMessage());
                    }
                });
                if (response.isSuccessful()) {
                    log.info("OkHttp3Util post success。[URL: {}], [body: {}], [response: {}]", url, json, result);
                } else {
                    log.info("OkHttp3Util post failure。[URL: {}], [body: {}], [response: {}]", url, json, result);
                }
                return result.toString();
            }
        } catch (Exception e) {
            log.error("OkHttp3Util post error : {}", e.getMessage());
            return "";
        }
    }

    public static String post(String url, Map<String, String> params) {
        try {
            OkHttpClient client = initOkHttpClient();

            FormBody.Builder builder = new FormBody.Builder();
            params.forEach(builder::add);
            FormBody formBody = builder.build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String result = Objects.requireNonNull(response.body()).string();
                if (response.isSuccessful()) {
                    log.info("OkHttp3Util post success。[URL: {}], [body: {}], [response: {}]", url, params, result);
                } else {
                    log.info("OkHttp3Util post failure。[URL: {}], [body: {}], [response: {}]", url, params, result);
                }
                return result;
            }
        } catch (Exception e) {
            log.error("OkHttp3Util post error : {}", e.getMessage());
            return "";
        }
    }

    /**
     * 上传文件
     * @param url 请求地址
     * @param filePath 文件路径
     * @param prefixPath yyw
     * @return 相应结果
     */
    public static String postFormData(String url, String filePath, String prefixPath){
        try {
            OkHttpClient client = initOkHttpClient();

            File file = new File(filePath);
            MultipartBody multipartBody = new MultipartBody.Builder()
                    .addFormDataPart("file", file.getName(), RequestBody.create(file, FORMDATA_MEDIATYPE))
                    .addFormDataPart("path", File.separator + prefixPath)
                    .setType(FORMDATA_MEDIATYPE)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(multipartBody)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String result = Objects.requireNonNull(response.body()).string();
                if (response.isSuccessful()) {
                    log.info("OkHttp3Util post_form_data success。[URL: {}], [body: {}], [response: {}]", url, multipartBody, result);
                } else {
                    log.info("OkHttp3Util post_form_data failure。[URL: {}], [body: {}], [response: {}]", url, multipartBody, result);
                }
                return result;
            }
        } catch (Exception e) {
            log.error("OkHttp3Util post_form_data error : {}", e.getMessage());
            return "";
        }
    }

    static void asyncGet(String url, Callback callback) {
        OkHttpClient client = initOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }
}
