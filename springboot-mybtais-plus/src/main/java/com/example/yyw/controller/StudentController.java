package com.example.yyw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.yyw.modal.Student;
import com.example.yyw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/6/26 22:33
 * @describe
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/selectById")
    public Student selectById(@RequestParam Long id){
        return studentService.getById(id);
    }

    @RequestMapping("/selectList")
    public List<Student> selectList(){
        return studentService.list();
    }

    @RequestMapping("/selectBatchIds")
    public Object selectBatchIds(@RequestParam List<Long> ids){
        return studentService.listByIds(ids);
    }

    @RequestMapping("/selectByMap")
    public Object selectByMap(String value){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name",value);
        return studentService.listByMap(columnMap);
    }

    @RequestMapping("/getOne")
    public Object getOne(String value){
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>();
        wrapper.lambda().eq(Student::getName, value);
        return studentService.getOne(wrapper);
    }

}
