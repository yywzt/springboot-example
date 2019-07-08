package com.example.yyw.jpa.service;

import com.example.yyw.jpa.modal.Student;
import com.example.yyw.jpa.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/8 15:33
 * @describe
 */
@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll(){
        List<Student> all = studentRepository.findAll();
        log.info("student: {}", all);
        return all;
    }
}
