package com.example.yyw.mock;

import com.example.yyw.xmlyService.modal.Category;
import com.example.yyw.xmlyService.service.XiMaLaYaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/9 16:07
 * @describe
 */
@Slf4j
public class MockTest {

    @Test
    public void test4(){
        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setCategoryName("分类1");
        categories.add(category);

        XiMaLaYaService xiMaLaYaService = mock(XiMaLaYaService.class);
        when(xiMaLaYaService.getCategoryList()).thenReturn(categories);

        List<Category> categoryList = xiMaLaYaService.getCategoryList();
        log.info("categoryList : {}",categoryList);

        Assert.assertEquals(categories,categoryList);
    }
}
