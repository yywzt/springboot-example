package com.example.yyw.controller;

import com.example.yyw.service.transaction.NoTransactionService;
import com.example.yyw.service.transaction.TestTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/6/25 17:52
 * @describe 测试springs事务
 *        同一个方法内 非事务方法内部调用事务方法（@Transactional）会导致事务失效
 *        原因：在应用系统调用声明@Transactional 的目标方法时，Spring Framework 默认使用 AOP 代理，
 *              在代码运行时生成一个代理对象，再由这个代理对象来统一管理，当在Service实现类直接调用内部方法时，其本质是通过this对象来调用的方法，而不是代理对象，因为会出现事务失效的情况
 *          解决方法：1、遇到方法自调用问题，显示通代理对象调用 见 noTransation2_1
 *                   2、如noTransation3方法所示
 *                   3、通过SpringUtils.getBean(this.getClass());然后调用 其方法，见noTransation2_2
 */
@RestController
public class TestTransactionController {

    @Autowired
    private TestTransactionService testTransactionService;
    @Autowired
    private NoTransactionService noTransactionService;

    @RequestMapping("/transation1")
    public void transation1(){
        testTransactionService.transation1();
    }
    @RequestMapping("/noTransation1")
    public void noTransation1(){
        noTransactionService.noTransation1();
    }

    /**
     * 失效事务
     */
    @RequestMapping("/noTransation2")
    public void noTransation2(){
        testTransactionService.noTransation2();
    }
    /**
     * 有效事务
     */
    @RequestMapping("/noTransation2_1")
    public void noTransation2_1(){
        testTransactionService.noTransation2_1();
    }
    /**
     * 有效事务
     */
    @RequestMapping("/noTransation2_2")
    public void noTransation2_2(){
        testTransactionService.noTransation2_2();
    }

    /**
     * 有效事务
     */
    @RequestMapping("/noTransation3")
    public void noTransation3(){
        noTransactionService.noTransation2();
    }
}
