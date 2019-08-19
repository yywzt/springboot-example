package com.example.yyw.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/15 22:30
 */
@Slf4j
public class TestOkHttp3 {

    private static final String DO_MAIN = "http://127.0.0.1:18081/";
    private static final String USERINF_FIND_ALL_URL = DO_MAIN + "userinf/findAll";
    private static final String USERINF_FIND_BY_USERNAME = DO_MAIN + "userinf/findUserInfByUname";
    private static final String USERINF_SAVE_URL = DO_MAIN + "userinf/save";

    private static final String UPLOAD_FILE = "http://127.0.0.1:18082/upload/file";

    @Test
    public void test_get() {
        OkHttp3Util.get(USERINF_FIND_ALL_URL);
    }

    @Test
    public void test_post() {
        String json = "{\"id\":10,\"createdBy\":null,\"creationDate\":null,\"updatedBy\":\"-1\",\"updationDate\":null,\"enabledFlag\":1,\"uname\":null,\"passwd\":null,\"gentel\":null,\"email\":null,\"city\":null,\"roles\":null}";
        String result = OkHttp3Util.post(USERINF_SAVE_URL, json, OkHttp3Util.getJsonMediatype());
        log.info(result);
    }
    @Test
    public void test_post2() {
        String result = OkHttp3Util.post(USERINF_FIND_BY_USERNAME, new HashMap<String, String>() {{
            put("username", "aaa");
        }});
        log.info(result);
    }

    @Test
    public void test_async_get() {
        OkHttp3Util.asyncGet(USERINF_FIND_ALL_URL, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.error("OkHttp3Util Callback error : {}", e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                log.info("OkHttp3Util Callback success : {}", Objects.requireNonNull(response.body()).string());
            }
        });
    }

    @Test
    public void test_upload_file(){
        String path = "C:\\Users\\yw\\Pictures\\bf85efd8-c56c-4ae8-aa5c-e887f4eb9bb1.jpeg";
        String prefixPath = "yyw";
        String result = OkHttp3Util.postFormData(UPLOAD_FILE, path, prefixPath);
        log.info(result);
    }
    
}
