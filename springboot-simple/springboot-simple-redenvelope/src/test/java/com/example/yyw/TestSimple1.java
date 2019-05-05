package com.example.yyw;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/30 14:34
 * @describe
 */
public class TestSimple1 {

    @Test
    public void test1(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("a",10,10));
        students.add(new Student("b",20,20));
        students.add(new Student("c",30,30));
        students.add(new Student("d",40,40));
        students.add(new Student("e",50,50));
        System.out.println(students);

        getUsedNum(students);

        System.out.println(students);
    }

    public void getUsedNum(List<Student> students){
        students.stream().forEach(student -> {
            student.setNum1(student.getNum1() + student.getNum2());
        });
    }
}
