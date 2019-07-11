package com.example.yyw.jpa;

import com.example.yyw.jpa.modal.Student;
import com.example.yyw.jpa.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

    @Autowired
    private StudentService studentService;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void contextLoads() {
        studentService.getAll();
    }

    @Test
    public void test_1() {
        String sql = "select * from student where sex = ?1 order by CONVERT(`name` using gbk) asc";
        Query nativeQuery = em.createNativeQuery(sql, Student.class);
        nativeQuery.setParameter(1, 1);
        List<Student> resultList = nativeQuery.getResultList();
        System.out.println(resultList);

        sql = "select * from student where sex = :sex order by CONVERT(`name` using gbk) asc";
        nativeQuery = em.createNativeQuery(sql, Student.class);
        nativeQuery.setParameter("sex", 1);
        resultList = nativeQuery.getResultList();
        System.out.println(resultList);

        sql = "select * from student where sex = :sex order by CONVERT(`name` using gbk) asc";
        nativeQuery = em.createNativeQuery(sql, Student.class);
        nativeQuery.setParameter("sex", 1);
        resultList = nativeQuery.setFirstResult(1).setMaxResults(2).getResultList();
        System.out.println(resultList);
    }

    @Test
    public void test_2() {
        String sql = "from Student order by name asc";
        List<Student> resultList = em.createQuery(sql, Student.class).getResultList();
        System.out.println(resultList);
    }

}
