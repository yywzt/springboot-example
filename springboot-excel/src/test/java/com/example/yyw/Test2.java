package com.example.yyw;

import com.example.yyw.easyexcel.util.WriteExcelUtil;
import com.example.yyw.mapper.HotelPolicy;
import com.example.yyw.mapper.HotelPolicyMapper;
import com.example.yyw.modal.ExcelPropertyIndexModel2;
import com.example.yyw.util.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.example.yyw.easyexcel.util.ReadExcelUtil.read;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/9/16 22:31
 * @describe
 */
@Slf4j
public class Test2 {

    @Test
    public void test_1() throws FileNotFoundException {
        double max = 99999D;
        List<ExcelPropertyIndexModel2> read = (List<ExcelPropertyIndexModel2>) read("D:\\yw\\workspace\\yyw\\springboot-example\\springboot-excel\\src\\test\\resources\\20190916.xlsx", ExcelPropertyIndexModel2.class);
        List<ArrayList<ExcelPropertyIndexModel2>> arrayLists = fairDivision(read, max);
        System.out.println(arrayLists);
        for(int i=0;i<arrayLists.size();i++){
            WriteExcelUtil.write("D:\\" + System.currentTimeMillis() + ".xlsx", arrayLists.get(i), ExcelPropertyIndexModel2.class);
        };
    }

    private List<ArrayList<ExcelPropertyIndexModel2>> fairDivision(List<ExcelPropertyIndexModel2> collect, double max) {
        List<Double> moneyList = collect.stream().map(ExcelPropertyIndexModel2::getMoney).collect(Collectors.toList());
        //分为多少组
        int ceil = (int) Math.ceil(getSum(moneyList) / max);

        ArrayList<ArrayList<ExcelPropertyIndexModel2>> resultList = new ArrayList<>(ceil);
        if (CollectionUtils.isEmpty(moneyList)) {
            return resultList;
        } else if (moneyList.size() == ceil) {
            for (int i = 0; i < moneyList.size(); i++) {
                ArrayList<ExcelPropertyIndexModel2> doubles = new ArrayList<>();
                doubles.add(collect.get(i));
                resultList.add(doubles);
            }
            return resultList;
        }

        //初始化k个集合
        for (int i = 0; i < ceil; i++) {
            resultList.add(new ArrayList<ExcelPropertyIndexModel2>());
        }

        int size = moneyList.size();
        Collections.sort(moneyList);
        List<ExcelPropertyIndexModel2> collect1 = collect.stream().sorted((o1, o2) -> {
            BigDecimal subtract = BigDecimalUtil.subtract(o1.getMoney(), o2.getMoney());
            return subtract.doubleValue() > 0D ? 1 :
                    subtract.doubleValue() < 0D ? -1 : 0;
        }).collect(Collectors.toList());
        log.info("sort after: {}", moneyList);
        log.info("sort after: {}", collect1);

        for (int i = 0; i < ceil; i++) {
            resultList.get(i).add(collect1.get(size - i - 1));
        }

        for (int i = size - ceil - 1; i >= 0; i--) {
            ArrayList<Double> tempSum = new ArrayList<>(ceil);
            for (ArrayList<ExcelPropertyIndexModel2> excelPropertyIndexModel2s : resultList) {
                List<Double> doubles = excelPropertyIndexModel2s.stream().map(ExcelPropertyIndexModel2::getMoney).collect(Collectors.toList());
                tempSum.add(getSum(doubles) + moneyList.get(i));
            }
            int minIndex = findMinIndex(tempSum);
            resultList.get(minIndex).add(collect1.get(i));
        }

        return resultList;
    }

    /**
     * @return The index of the min member
     */
    private static int findMinIndex(ArrayList<Double> list) {
        int res = 0;
        double t = Double.MAX_VALUE;
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index) < t) {
                t = list.get(index);
                res = index;
            }
        }
        return res;
    }

    /**
     * @return Sum of list members
     */
    private static double getSum(List<Double> list) {
        AtomicReference<BigDecimal> bigDecimal = new AtomicReference<>(new BigDecimal(0));
        list.forEach(aDouble -> {
            bigDecimal.set(BigDecimalUtil.add(bigDecimal.get(), BigDecimal.valueOf(aDouble)));
        });
        return bigDecimal.get().doubleValue();
    }

}
