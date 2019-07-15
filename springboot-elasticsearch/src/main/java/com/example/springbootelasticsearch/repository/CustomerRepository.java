package com.example.springbootelasticsearch.repository;

import com.example.springbootelasticsearch.modal.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/15 17:07
 * @describe
 */
public interface CustomerRepository extends ElasticsearchCrudRepository<Customer,String> {
}
