package com.example.springbootelasticsearch.service;

import com.example.springbootelasticsearch.modal.Student;
import com.example.springbootelasticsearch.repository.StudentRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.get.GetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/1 10:52
 * @describe
 */
@Service
public class StudentService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> findPage(Pageable pageable){
        pageable.getSortOr(Sort.by("creation_date").descending());
        return studentRepository.findAll(pageable);
    }
    public Optional<Student> findById(String id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional;
    }

    public List<Student> findByNameLike(String name, Pageable pageable){
        CriteriaQuery criteriaQuery = new CriteriaQuery(Criteria.where("name").contains(name), pageable);
        List<Student> studentList = elasticsearchTemplate.queryForList(criteriaQuery, Student.class);
        return studentList;
    }

    public UpdateResponse updateOne(String id){
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source("gender", "0");
        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(id).withClass(Student.class).withIndexRequest(indexRequest).build();
        return elasticsearchTemplate.update(updateQuery);
    }

}
