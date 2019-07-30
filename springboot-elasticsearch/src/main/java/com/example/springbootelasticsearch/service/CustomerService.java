package com.example.springbootelasticsearch.service;

import com.example.springbootelasticsearch.modal.Customer;
import com.example.springbootelasticsearch.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/15 17:09
 * @describe
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public Customer findById(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }
        return null;
    }

    public Customer save(Customer customer) {
        customer.setCreatedBy("-1");
        customer.setUpdatedBy("-1");
        customer.setCreationDate(new Date());
        customer.setUpdationDate(new Date());
        Customer save = customerRepository.save(customer);
        return save;
    }

    public void saveAll(List<Customer> customerList) {
        customerRepository.saveAll(customerList);
    }

    public List<Customer> findAll() {
        Sort sort = Sort.by("_id").ascending();
        Iterable<Customer> customerIterable = customerRepository.findAll(sort);
        List<Customer> customerList = new ArrayList<>();
        customerIterable.forEach(customer -> customerList.add(customer));
        return customerList;
    }

    public Page<Customer> page() {
        Sort sort = Sort.by("_id").ascending();
        PageRequest pageable = PageRequest.of(0, 20, sort);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return customerPage;
    }

    public List<Customer> selectAll() {
        Iterable<Customer> customerIterable = customerRepository.findAll();
        List<Customer> customerList = new ArrayList<>();
        customerIterable.forEach(customer -> customerList.add(customer));
        return customerList;
    }
}