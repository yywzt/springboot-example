package com.example.yyw.stream;

import com.example.yyw.stream.modal.MarkPhoto;
import com.example.yyw.util.DateUtil;
import org.junit.Test;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/12 15:34
 * @describe
 */
public class ListGroupTest {

    private static final List<Long> USER_ID_LIST = Arrays.asList(1058L, 1059L, 1060L);
    private static final List<String> CHANNEL_ID_LIST = Arrays.asList("AA1090", "AA1000", "AA1100");
    private static final List<String> CITY_LIST = Arrays.asList("深圳", "北京", "广州");
    private static final List<String> DATE_LIST = Arrays.asList("2019-07-09", "2019-07-10", "2019-07-11");
    private static final String TITLE = "photo";

    public List<MarkPhoto> buildMarkPhoto_many() {
        List<MarkPhoto> markPhotoList = new LinkedList<>();
        buildMarkPhoto(16L, markPhotoList, "AA1090", 1058L, "北京", TITLE, "2019-07-11", "2019-07-11 12:00:00");
        buildMarkPhoto(17L, markPhotoList, "AA1090", 1058L, "北京", TITLE, "2019-07-11", "2019-07-11 13:00:00");
        buildMarkPhoto(15L, markPhotoList, "AA1090", 1058L, "北京", TITLE, "2019-07-11", "2019-07-11 14:00:00");
        buildMarkPhoto(23L, markPhotoList, "AA1090", 1058L, "广州", TITLE, "2019-07-11", "2019-07-11 21:00:00");
        buildMarkPhoto(25L, markPhotoList, "AA1090", 1058L, "广州", TITLE, "2019-07-11", "2019-07-11 22:00:00");
        buildMarkPhoto(24L, markPhotoList, "AA1090", 1058L, "广州", TITLE, "2019-07-11", "2019-07-11 23:00:00");
        buildMarkPhoto(8L, markPhotoList, "AA1090", 1058L, "深圳", TITLE, "2019-07-11", "2019-07-11 02:00:00");
        buildMarkPhoto(9L, markPhotoList, "AA1090", 1058L, "深圳", TITLE, "2019-07-11", "2019-07-11 03:00:00");
        buildMarkPhoto(13L, markPhotoList, "AA1090", 1058L, "北京", TITLE, "2019-07-10", "2019-07-10 12:00:00");
        buildMarkPhoto(14L, markPhotoList, "AA1090", 1058L, "北京", TITLE, "2019-07-10", "2019-07-10 13:00:00");
        return markPhotoList;
    }

    private void buildMarkPhoto(Long id, List<MarkPhoto> markPhotoList, String channelId, Long uid, String city, String title, String date, String createDate) {
        MarkPhoto markPhoto = new MarkPhoto();
        markPhoto.setId(id);
        markPhoto.setTitle(title);
        markPhoto.setUserId(uid);
        markPhoto.setChannelId(channelId);
        markPhoto.setDate(new java.sql.Date(DateUtil.stringToDate(date, "yyyy-MM-dd").getTime()));
        markPhoto.setCity(city);
        markPhoto.setCreateDate(DateUtil.stringToDate(createDate, "yyyy-MM-dd HH:mm:ss"));
        markPhotoList.add(markPhoto);
    }

    @Test
    public void test_list_stream_group() {
        List<MarkPhoto> markPhotoList = buildMarkPhoto_many();
        System.out.println("markPhotoList: " + markPhotoList);

        //避免分组后顺序紊乱（与之前排序好的顺序有误差）
        Map<java.sql.Date, Map<String, List<MarkPhoto>>> collect = markPhotoList.stream().collect(Collectors.groupingBy(MarkPhoto::getDate, LinkedHashMap::new,
                Collectors.groupingBy(MarkPhoto::getCity, LinkedHashMap::new, Collectors.toList())));
        System.out.println("collect: " + collect);

    }

    @Test
    public void test_() {
        List<String> sort = new ArrayList<String>() {{
            add("id,asc");
            add("createDate,desc");
            add("");
        }};
        sort.stream().forEach(s -> {
            List<String> strings = Arrays.asList(s.split(","));
            String properties = strings.get(0);
            String direction = strings.get(1);
            System.out.println(properties);
            System.out.println(direction);
        });
    }
}
