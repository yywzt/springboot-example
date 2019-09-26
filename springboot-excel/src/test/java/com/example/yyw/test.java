package com.example.yyw;

import com.example.yyw.util.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/9/16 20:05
 * @describe
 */
@Slf4j
public class test {

    @Test
    public void test_1(){

    }

    // sum 635106.17 7
    @Test
    public void test_2(){
        Double[] a = {8642.04D, 8380.16D, 1305.26D, 1305.26D, 1695D, 5085D, 5085D, 14068.5D, 1103.46D, 1471.28D, 1030.56D, 772.92D, 1052.6D, 526.3D, 6102D, 440.7D, 13397.28D, 5593.5D, 5593.5D, 4746D, 115.26D, 1523.8D, 1523.8D, 711.9D, 3898.5D, 1966.24D, 1017D, 1220.4D, 194.9D, 3798.5D, 559.35D, 16102.5D, 9999.99D, 75241.66D, 61963.72D, 44259.8D, 4068D, 2491.8D, 2491.8D, 2156.04D, 76.3D, 228.8D, 3529.96D, 3495.8D, 96.71D, 2440.8D, 2440.8D, 2752.68D, 106.83D, 503.58D, 1542.5D, 93.2D, 228.8D, 1279.7D, 33.9D, 644.1D, 1152.6D, 313.6D, 351.7D, 5619.25D, 127.25D, 122.16D, 234.14D, 122.16D, 33.9D, 101.7D, 93.2D, 432.2D, 161D, 161D, 194.9D, 84.8D, 55.1D, 93.2D, 67.8D, 67.8D, 67.8D, 381.4D, 46.6D, 254.3D, 33.9D, 178D, 372.9D, 381.4D, 372.9D, 135.6D, 135.6D, 135.6D, 127.1D, 271.2D, 127.1D, 93.2D, 644.1D, 178D, 135.6D, 135.6D, 432.2D, 1339.1D, 330.5D, 237.3D, 3257.79D, 367.85D, 114.4D, 101.7D, 135.6D, 121.22D, 149.16D, 121.22D, 139.81D, 139.81D, 149.16D, 214.39D, 101.7D, 67.8D, 156.8D, 33.9D, 25.45D, 2631.66D, 495.9D, 99.06D, 1640.8D, 364.4D, 2222.84D, 22.04D, 394.1D, 22.04D, 1681.44D, 101.7D, 587.3D, 427.14D, 587.3D, 264.42D, 149.16D, 783.09D, 352.56D, 88.14D, 352.56D, 216.96D, 620.37D, 379.84D, 33.9D, 3239.25D, 44.07D, 71.19D, 122.04D, 857.44D, 3132.36D, 1495.08D, 3546.4D, 37.29D, 44.07D, 451.75D, 2687.37D, 2383.17D, 91.53D, 7146.12D, 81.36D, 622.86D, 162.72D, 94.92D, 8461.44D, 149.16D, 162.72D, 74.58D, 54.24D, 1006.8D, 1952.64D, 10679.2D, 1818.88D, 6874.92D, 1818.88D, 61.02D, 1818.88D, 1449.32D, 2039.86D, 354.16D, 3783.24D, 176.32D, 1603.47D, 10088.64D, 2212.12D, 2405.42D, 1320.5D, 11191.62D, 33.9D, 25.42D, 1817.04D, 28.82D, 5977D, 28.82D, 30.52D, 1157.1D, 1066.24D, 1895.01D, 2328.25D, 711.9D, 3607.7D, 2623.86D, 30.52D, 813.6D, 3607.7D, 922.24D, 3342.54D, 1334.9D, 2644.2D, 186.45D, 2291.64D, 623.84D, 105.09D, 315.27D, 372.9D, 372.9D, 50.85D, 149.12D, 209.3D, 209.3D, 799.09D, 143.26D, 61.02D, 1619.52D, 55.99D, 1619.52D, 427.14D, 55.99D, 154.28D, 111.84D, 176.28D, 176.28D, 176.28D, 2479.1D, 102.52D, 108.48D, 5095.08D, 1637.37D};
        Double[] b = {44259.8D, 5593.5D, 5085.0D, 3798.5D, 3546.4D, 3239.25D, 2631.66D, 2440.8D, 2291.64D, 1966.24D, 1818.88D, 1619.52D, 1495.08D, 1305.26D, 1103.46D, 922.24D, 772.92D, 620.37D, 495.9D, 427.14D, 372.9D, 354.16D, 313.6D, 228.8D, 194.9D, 176.28D, 156.8D, 149.16D, 139.81D, 127.1D, 121.22D, 102.52D, 101.7D, 93.2D, 67.8D, 67.8D, 55.99D, 33.9D, 30.52D, 22.04};
        log.info("{}", a);
        log.info("{}", getSum(Arrays.asList(a)));
        log.info("{}", b);
        log.info("{}", getSum(Arrays.asList(b)));
    }

    @Test
    public void test_3(){
//        double[] b = {8642.04D, 8380.16D, 1305.26D, 1305.26D, 1695D, 5085D, 5085D, 14068.5D, 1103.46D, 1471.28D, 1030.56D, 772.92D, 1052.6D, 526.3D, 6102D, 440.7D, 13397.28D, 5593.5D, 5593.5D, 4746D, 115.26D, 1523.8D, 1523.8D, 711.9D, 3898.5D, 1966.24D, 1017D, 1220.4D, 194.9D, 3798.5D, 559.35D, 16102.5D, 9999.99D, 75241.66D, 61963.72D, 44259.8D, 4068D, 2491.8D, 2491.8D, 2156.04D, 76.3D, 228.8D, 3529.96D, 3495.8D, 96.71D, 2440.8D, 2440.8D, 2752.68D, 106.83D, 503.58D, 1542.5D, 93.2D, 228.8D, 1279.7D, 33.9D, 644.1D, 1152.6D, 313.6D, 351.7D, 5619.25D, 127.25D, 122.16D, 234.14D, 122.16D, 33.9D, 101.7D, 93.2D, 432.2D, 161D, 161D, 194.9D, 84.8D, 55.1D, 93.2D, 67.8D, 67.8D, 67.8D, 381.4D, 46.6D, 254.3D, 33.9D, 178D, 372.9D, 381.4D, 372.9D, 135.6D, 135.6D, 135.6D, 127.1D, 271.2D, 127.1D, 93.2D, 644.1D, 178D, 135.6D, 135.6D, 432.2D, 1339.1D, 330.5D, 237.3D, 3257.79D, 367.85D, 114.4D, 101.7D, 135.6D, 121.22D, 149.16D, 121.22D, 139.81D, 139.81D, 149.16D, 214.39D, 101.7D, 67.8D, 156.8D, 33.9D, 25.45D, 2631.66D, 495.9D, 99.06D, 1640.8D, 364.4D, 2222.84D, 22.04D, 394.1D, 22.04D, 1681.44D, 101.7D, 587.3D, 427.14D, 587.3D, 264.42D, 149.16D, 783.09D, 352.56D, 88.14D, 352.56D, 216.96D, 620.37D, 379.84D, 33.9D, 3239.25D, 44.07D, 71.19D, 122.04D, 857.44D, 3132.36D, 1495.08D, 3546.4D, 37.29D, 44.07D, 451.75D, 2687.37D, 2383.17D, 91.53D, 7146.12D, 81.36D, 622.86D, 162.72D, 94.92D, 8461.44D, 149.16D, 162.72D, 74.58D, 54.24D, 1006.8D, 1952.64D, 10679.2D, 1818.88D, 6874.92D, 1818.88D, 61.02D, 1818.88D, 1449.32D, 2039.86D, 354.16D, 3783.24D, 176.32D, 1603.47D, 10088.64D, 2212.12D, 2405.42D, 1320.5D, 11191.62D, 33.9D, 25.42D, 1817.04D, 28.82D, 5977D, 28.82D, 30.52D, 1157.1D, 1066.24D, 1895.01D, 2328.25D, 711.9D, 3607.7D, 2623.86D, 30.52D, 813.6D, 3607.7D, 922.24D, 3342.54D, 1334.9D, 2644.2D, 186.45D, 2291.64D, 623.84D, 105.09D, 315.27D, 372.9D, 372.9D, 50.85D, 149.12D, 209.3D, 209.3D, 799.09D, 143.26D, 61.02D, 1619.52D, 55.99D, 1619.52D, 427.14D, 55.99D, 154.28D, 111.84D, 176.28D, 176.28D, 176.28D, 2479.1D, 102.52D, 108.48D, 5095.08D, 1637.37D};
        Double[] b = {8642.04D, 8380.16D, 1305.26D, 1305.26D, 1695D, 5085D, 5085D, 14068.5D, 1103.46D, 1471.28D, 1030.56D, 772.92D, 1052.6D};
        Double max = 15000D;
        List<Double> doubles1 = Arrays.asList(b);
        double ceil = Math.ceil(getSum(doubles1) / max);
        log.info("{}", ceil);
        log.info("{}", getSum(doubles1));
        List<ArrayList<Double>> arrayLists = fairDivision(b, (int) ceil);
        arrayLists.stream().forEach(doubles -> {
            log.info("{}", doubles);
            log.info("sum = {}", getSum(doubles));
        });

        arrayLists = fairDivision(doubles1, (int) ceil);
        arrayLists.stream().forEach(doubles -> {
            log.info("{}", doubles);
            log.info("sum = {}", getSum(doubles));
        });
    }

    private static List<ArrayList<Double>> fairDivision(Double[] input, int k) {
        ArrayList<ArrayList<Double>> resultList = new ArrayList<>(k);
        if (k > input.length || k < 0) {
            return resultList;
        } else if (k == input.length) {
            for (double c : input) {
                ArrayList<Double> t = new ArrayList<>();
                t.add(c);
                resultList.add(t);
            }
            return resultList;
        }

        for (int i = 0; i < k; i++) {
            resultList.add(new ArrayList<>());
        }

        Double[] sortedInput = input.clone();
        int inputLen = sortedInput.length;
        Arrays.sort(sortedInput);

        // 从最大的开始填充到结果中
        for (int i = 0; i < k; i++) {
            resultList.get(i).add(sortedInput[inputLen - 1 - i]);
        }
        // 从大到小遍历剩下的数字
        for (int i = inputLen - 1 - k; i >= 0; i--) {
            ArrayList<Double> tempSum = new ArrayList<>(k);
            for (List<Double> l : resultList) {
                tempSum.add(getSum(l) + sortedInput[i]);
            }
            int minIndex = findMinIndex(tempSum); // 找出结果最小的那个分组
            resultList.get(minIndex).add(sortedInput[i]); // 将当前数加入那个分组
        }

        return resultList;
    }

    private static List<ArrayList<Double>> fairDivision(List<Double> doubleList, int k){
        ArrayList<ArrayList<Double>> resultList = new ArrayList<>(k);
        if(CollectionUtils.isEmpty(doubleList)){
            return resultList;
        }else if(doubleList.size() == k){
            for (int i=0;i<doubleList.size();i++){
                ArrayList<Double> doubles = new ArrayList<>();
                doubles.add(doubleList.get(i));
                resultList.add(doubles);
            }
            return resultList;
        }

        //初始化k个集合
        for (int i=0;i<k;i++){
            resultList.add(new ArrayList<Double>());
        }

        int size = doubleList.size();
        Collections.sort(doubleList);
        log.info("sort after: {}", doubleList);

        for (int i=0;i<k;i++){
            resultList.get(i).add(doubleList.get(size - i - 1));
        }

        for (int i = size - k - 1; i >= 0; i--) {
            ArrayList<Double> tempSum = new ArrayList<>(k);
            for (ArrayList<Double> doubles : resultList){
                tempSum.add(getSum(doubles) + doubleList.get(i));
            }
            int minIndex = findMinIndex(tempSum);
            resultList.get(minIndex).add(doubleList.get(i));
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

