package com.example.springbootelasticsearch.controller;

import com.example.springbootelasticsearch.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/1 10:53
 * @describe
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/findPage")
    public Object findPage(Pageable pageable){
        return studentService.findPage(pageable);
    }

    @RequestMapping("/findById")
    public Object findById(String id){
        return studentService.findById(id);
    }

    @RequestMapping("/findByNameLike")
    public Object findByNameLike(String name, Pageable pageable){
        return studentService.findByNameLike(name, pageable);
    }

    @RequestMapping("/updateOne")
    public Object updateOne(String id){
        return studentService.updateOne(id);
    }

}
