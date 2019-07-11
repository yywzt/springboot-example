package com.example.yyw.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/10 19:18
 * @describe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void selectById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/selectById").param("id", "1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"createdBy\":\"-1\",\"creationDate\":\"2019-06-26 22:22:55\",\"updatedBy\":null,\"updationDate\":null,\"enabledFlag\":1,\"name\":\"a1\",\"gender\":\"1\",\"age\":12}"));
    }

    @Test
    public void selectList() {
    }

    @Test
    public void selectBatchIds() {
    }

    @Test
    public void selectByMap() {
    }

    @Test
    public void getOne() {
    }

    @Test
    @Transactional
    public void removeById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/selectById").param("id", "6"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":6,\"createdBy\":\"-1\",\"creationDate\":\"2019-06-26 22:22:55\",\"updatedBy\":null,\"updationDate\":null,\"enabledFlag\":1,\"name\":\"a6\",\"gender\":\"1\",\"age\":5}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/remove").param("id", "6"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        mockMvc.perform(MockMvcRequestBuilders.get("/selectById").param("id", "6"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }
}