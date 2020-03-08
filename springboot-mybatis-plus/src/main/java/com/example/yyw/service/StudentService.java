package com.example.yyw.service;

import com.example.yyw.mapper.ssm.StudentMapper;
import com.example.yyw.modal.Student;
import com.example.yyw.service.base.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/6/26 22:30
 * @describe
 */
@Service
@Slf4j
public class StudentService extends GenericService<Student> {

    @Autowired
    private StudentMapper studentMapper;
}
