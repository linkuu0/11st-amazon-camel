package org.link.camel.web.controller;

import org.junit.jupiter.api.Test;
import org.link.camel.domain.Item;
import org.link.camel.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

    @Test
    public void itemInfoMainTest() throws Exception {
        Long productId = 3548741298L;
        Item item = new Item(productId, null, null);
        when(itemService.getItemInfo(productId)).thenReturn(item);

        mockMvc.perform(get("/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(String.valueOf(productId)))
                .andDo(print());
    }

    @Test
    public void addItemTest() throws Exception {
        Long productId = 3548741298L;
        when(itemService.searchProductInfo(productId)).thenReturn(productId);

        mockMvc.perform(post("/products/{id}", productId))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

}