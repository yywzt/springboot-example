package com.example.yyw.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yyw.util.HttpUtil;
import com.example.yyw.util.JsonBinder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import kotlin.text.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/24 10:34
 * @Description
 */
@Slf4j
public class SendSmsTask {

    private static ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

    private static List<String> phoneList = Lists.newArrayList();

    private static List<DataBean> dataBeans = Lists.newArrayList();

    static {
        threadPoolTaskScheduler.setPoolSize(3);
        threadPoolTaskScheduler.initialize();
        readPhoneFromFile();
        initIpPort();
        log.info("phoneList size: ", phoneList.size());
        log.info("dataBeans size: ", dataBeans.size());
    }

    private static void initIpPort() {
        String result = HttpUtil.httpGet("https://ip.jiangxianli.com/api/proxy_ips");
        IpInfo ipInfo = JsonBinder.buildNonNullBinder().fromJson(result, IpInfo.class);
        dataBeans = Optional.ofNullable(ipInfo).map(IpInfo::getData).map(DataBeanX::getData).orElseGet(ArrayList::new);
    }

    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(30);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        startTask();
//        send(phoneList.get(20));
//        initIpPort();
    }

    private static void readPhoneFromFile() {
        try {
            InputStream inputStream = SendSmsTask.class.getClassLoader().getResourceAsStream("phone.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String s = "";
            do {
                stringBuffer.append(s);
                s = bufferedReader.readLine();
            }while (s != null);
            String phones = stringBuffer.toString();
            phoneList = Lists.newArrayList(phones.split(","));
        } catch (IOException e) {
            log.error("readPhoneFromFile: ", e);
        }
    }

    public static String send(String phone, DataBean dataBean) {
        Map<String, String> params = Maps.newHashMap();
        params.put("phone_number", phone);
        String result = HttpUtil.httpPost("http://hqcup.yttsp.com/juneVeh/verifyCode.action", params, dataBean.getIp(), dataBean.getPort(), dataBean.getProtocol());
        log.info("result: {}", result);
        return result;
    }

    public static void startTask() {
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                int i = RandomUtils.nextInt(0, phoneList.size() + 1);
                int j = RandomUtils.nextInt(0, dataBeans.size() + 1);
                send(phoneList.get(i), dataBeans.get(j));
            }
        }, 1L, 200L, TimeUnit.MILLISECONDS);
    }

}
