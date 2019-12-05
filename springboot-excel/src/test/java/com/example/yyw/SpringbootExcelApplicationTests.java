package com.example.yyw;

import com.example.yyw.mapper.HotelPolicy;
import com.example.yyw.mapper.HotelPolicyMapper;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.yyw.easyexcel.util.ReadExcelUtil.read;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootExcelApplicationTests {

    @Autowired HotelPolicyMapper HotelPolicyMapper;
    @Test
    public void contextLoads() {

    }

    @Test
    public void test_7(){
        List<HotelPolicy> read = (List<HotelPolicy>) read("D:\\yw\\workspace\\yyw\\springboot-example\\springboot-excel\\src\\test\\resources\\酒店须知PoliciesInfo_0905.xls", HotelPolicy.class);
        System.out.println(read.size());
        List<List<HotelPolicy>> partition = Lists.partition(read, 30);
        partition.forEach(hotelPolicies -> HotelPolicyMapper.insertList(hotelPolicies));
    }
}
