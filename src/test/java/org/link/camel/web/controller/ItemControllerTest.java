package org.link.camel.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

//    @Before
//    public void before() {
//        mockMvc = MockMvcBuilders.standaloneSetup(ItemControllerTest.class)
//                .alwaysExpect(MockMvcResultMatchers.status().isOk())
//                .build();
//    }

    @Test
    public void itemInfoMainTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/3548741298"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("17"));
    }

}