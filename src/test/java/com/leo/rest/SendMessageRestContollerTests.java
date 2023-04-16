package com.leo.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.leo.rabbitmq.rest.SendMessageRestController;
 
//使用springboot和junit 生成SendMessageRestController的测试类
@SpringBootTest(classes = SendMessageRestController.class)
@AutoConfigureMockMvc
public class SendMessageRestContollerTests {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void test() {
        assertEquals(1, 1);
    }
    //生成index的测试
    //q: 生成SendMessageRestController.index()的单元测试
    //a: 生成的单元测试代码如下
    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/index")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("index"));
    }
}

