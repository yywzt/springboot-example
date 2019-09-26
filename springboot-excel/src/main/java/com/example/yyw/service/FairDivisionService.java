package com.example.yyw.service;

import com.example.yyw.easyexcel.util.WriteExcelUtil;
import com.example.yyw.modal.ExcelPropertyIndexModel2;
import com.example.yyw.util.BigDecimalUtil;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

import static com.example.yyw.easyexcel.util.ReadExcelUtil.read;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/9/21 13:27
 */
@Slf4j
@Service
public class FairDivisionService {

    public void groupExcel(HttpServletResponse response, MultipartFile multipartFile, double max) {
        List<Object> read = read(multipartFile, ExcelPropertyIndexModel2.class);
        List<ExcelPropertyIndexModel2> collect = Optional.ofNullable(read).orElseGet(ArrayList::new).stream().map(o -> (ExcelPropertyIndexModel2) o).collect(Collectors.toList());
        List<ArrayList<ExcelPropertyIndexModel2>> arrayLists = fairDivision(collect, max);

        try {
            // 设置浏览器返回的文件类型和文件名
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition","attachment;filename=" + URLEncoder.encode(UUID.randomUUID().toString() + ".zip", "UTF-8"));
            response.setBufferSize(1024);
            // 开启zip输出流
            @Cleanup ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());

            for (ArrayList<ExcelPropertyIndexModel2> arrayList : arrayLists) {
                WriteExcelUtil.write(arrayList, ExcelPropertyIndexModel2.class, zos);
            }
            zos.flush();
        } catch (IOException e) {
            log.error("导出Excel error: {}", e);
        }
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
            resultList.add(new ArrayList<>());
        }

        int size = moneyList.size();
        Collections.sort(moneyList);
        List<ExcelPropertyIndexModel2> collect1 = collect.stream().sorted((o1, o2) -> {
            BigDecimal subtract = BigDecimalUtil.subtract(o1.getMoney(), o2.getMoney());
            return Double.compare(subtract.doubleValue(), 0D);
        }).collect(Collectors.toList());

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
        list.forEach(aDouble -> bigDecimal.set(BigDecimalUtil.add(bigDecimal.get(), BigDecimal.valueOf(aDouble))));
        return bigDecimal.get().doubleValue();
    }

}
