package com.example.springbootelasticsearch.repository;

import com.example.springbootelasticsearch.modal.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/1 10:50
 * @describe
 */
public interface StudentRepository extends ElasticsearchRepository<Student, String> {
}
