package com.example.yyw;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/4/28 17:16
 * @describe
 */
@Slf4j
public class Tests {

    @Test
    public void test1() {
        String s = "{\"data\":{\"id\":1122428091394961408,\"createDate\":1556442685351,\"modifyDate\":1556442685351,\"name\":\"测试2144\",\"hxAccount\":\"2075b65fbd68f6d2c09cb6dda5fa6ab9\",\"hxPassword\":\"cc201ba4bbebd9c1030ba1a6eea79230\",\"subAccount\":\"\",\"subToken\":\"\",\"voipAccount\":\"\",\"voipPassword\":\"\",\"phone\":\"11000002144\",\"plateNumber\":\"\",\"resultCode\":\"2\",\"isOnLine\":\"0\",\"carTypeId\":338,\"carType\":\"奔腾\",\"carTypeLogo\":\"http://www.carbuyin.net/by2/carBrand/d804ef97-503f-4fc4-87e6-10f0ae59d764.png\",\"isRead\":0,\"gender\":\"0\",\"mkUser\":\"\",\"mkPassword\":\"\",\"picPath\":\"http://www.carbuyin.net/by3/userHeader/default_01.png\",\"age\":\"17\",\"distance\":\"0.00\",\"hxAccountService\":\"3af92f4387698a71cd0092b7500582f5\",\"hxPasswordService\":\"b16ef8ce2bf9c3b6d056255b30a6b336\",\"voipAccountService\":\"\",\"voipPasswordService\":\"\",\"isFirstCar\":false,\"type\":\"2\",\"birthDay\":\"2001-04-28\",\"birthDayLong\":0,\"enableStatus\":\"1\",\"headerIndex\":\"\",\"openId\":\"xm_SSEjbL9YI2oiIBIHP7I\",\"channelId\":\"AA1090\",\"score\":0,\"h5Score\":0,\"tboxSN\":\"\",\"bmUserId\":\"\",\"wxOpenId\":\"\",\"privateStatus\":\"1\",\"beanNum\":0,\"userShortNum\":\"317407\",\"riskScore\":\"0\"},\"resultCode\":\"1\",\"resultMessage\":\"操作成功\"}";
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(s);
            if (StringUtils.isNotBlank(String.valueOf(jsonObject.getJSONObject("data").getLong("id"))) || StringUtils.isNotBlank(String.valueOf(jsonObject.getLong("id")))) {
                log.info("激活成功！");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        String s = "{\"id\":1122428091394961408,\"createDate\":1556442685351,\"modifyDate\":1556442685351,\"name\":\"测试2144\",\"hxAccount\":\"2075b65fbd68f6d2c09cb6dda5fa6ab9\",\"hxPassword\":\"cc201ba4bbebd9c1030ba1a6eea79230\",\"subAccount\":\"\",\"subToken\":\"\",\"voipAccount\":\"\",\"voipPassword\":\"\",\"phone\":\"11000002144\",\"plateNumber\":\"\",\"resultCode\":\"2\",\"isOnLine\":\"0\",\"carTypeId\":338,\"carType\":\"奔腾\",\"carTypeLogo\":\"http://www.carbuyin.net/by2/carBrand/d804ef97-503f-4fc4-87e6-10f0ae59d764.png\",\"isRead\":0,\"gender\":\"0\",\"mkUser\":\"\",\"mkPassword\":\"\",\"picPath\":\"http://www.carbuyin.net/by3/userHeader/default_01.png\",\"age\":\"17\",\"distance\":\"0.00\",\"hxAccountService\":\"3af92f4387698a71cd0092b7500582f5\",\"hxPasswordService\":\"b16ef8ce2bf9c3b6d056255b30a6b336\",\"voipAccountService\":\"\",\"voipPasswordService\":\"\",\"isFirstCar\":false,\"type\":\"2\",\"birthDay\":\"2001-04-28\",\"birthDayLong\":0,\"enableStatus\":\"1\",\"headerIndex\":\"\",\"openId\":\"xm_SSEjbL9YI2oiIBIHP7I\",\"channelId\":\"AA1090\",\"score\":0,\"h5Score\":0,\"tboxSN\":\"\",\"bmUserId\":\"\",\"wxOpenId\":\"\",\"privateStatus\":\"1\",\"beanNum\":0,\"userShortNum\":\"317407\",\"riskScore\":\"0\"}";
//        String s = "{\"data\":{\"id\":1122428091394961408,\"createDate\":1556442685351,\"modifyDate\":1556442685351,\"name\":\"测试2144\",\"hxAccount\":\"2075b65fbd68f6d2c09cb6dda5fa6ab9\",\"hxPassword\":\"cc201ba4bbebd9c1030ba1a6eea79230\",\"subAccount\":\"\",\"subToken\":\"\",\"voipAccount\":\"\",\"voipPassword\":\"\",\"phone\":\"11000002144\",\"plateNumber\":\"\",\"resultCode\":\"2\",\"isOnLine\":\"0\",\"carTypeId\":338,\"carType\":\"奔腾\",\"carTypeLogo\":\"http://www.carbuyin.net/by2/carBrand/d804ef97-503f-4fc4-87e6-10f0ae59d764.png\",\"isRead\":0,\"gender\":\"0\",\"mkUser\":\"\",\"mkPassword\":\"\",\"picPath\":\"http://www.carbuyin.net/by3/userHeader/default_01.png\",\"age\":\"17\",\"distance\":\"0.00\",\"hxAccountService\":\"3af92f4387698a71cd0092b7500582f5\",\"hxPasswordService\":\"b16ef8ce2bf9c3b6d056255b30a6b336\",\"voipAccountService\":\"\",\"voipPasswordService\":\"\",\"isFirstCar\":false,\"type\":\"2\",\"birthDay\":\"2001-04-28\",\"birthDayLong\":0,\"enableStatus\":\"1\",\"headerIndex\":\"\",\"openId\":\"xm_SSEjbL9YI2oiIBIHP7I\",\"channelId\":\"AA1090\",\"score\":0,\"h5Score\":0,\"tboxSN\":\"\",\"bmUserId\":\"\",\"wxOpenId\":\"\",\"privateStatus\":\"1\",\"beanNum\":0,\"userShortNum\":\"317407\",\"riskScore\":\"0\"},\"resultCode\":\"1\",\"resultMessage\":\"操作成功\"}";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);

            JSONObject data = null;
            try {
                data = jsonObject.getJSONObject("data");
            } catch (JSONException e) {
//                e.printStackTrace();
                log.error(e.getMessage());
            }
//            if (data == null && StringUtils.isNotBlank(String.valueOf(jsonObject.getLong("id")))) {
//                log.info("激活成功1！");
//            }
//            if (data != null && StringUtils.isNotBlank(String.valueOf(data.getLong("id")))) {
//                log.info("激活成功2！");
//            }
            if (data == null ? StringUtils.isNotBlank(String.valueOf(jsonObject.getLong("id"))) : StringUtils.isNotBlank(String.valueOf(data.getLong("id")))) {
                log.info("激活成功");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        String s = " ab sad  ";
        System.out.println(s);
        String s1 = s.replaceAll("\\s*", "");
        System.out.println(s1);
    }

}
