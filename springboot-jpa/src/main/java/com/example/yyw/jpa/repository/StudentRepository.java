package com.example.yyw.jpa.repository;

import com.example.yyw.jpa.modal.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/8 15:32
 * @describe
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
}
