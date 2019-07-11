package com.example.yyw.jpa.controller;

import com.example.yyw.jpa.modal.Student;
import com.example.yyw.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/8 15:38
 * @describe
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/getAll")
    public List<Student> getAll(){
        return studentService.getAll();
    }
}
