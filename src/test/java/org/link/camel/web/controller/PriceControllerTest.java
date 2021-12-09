package org.link.camel.web.controller;

import org.junit.jupiter.api.Test;
import org.link.camel.domain.Item;
import org.link.camel.domain.Price;
import org.link.camel.service.ItemService;
import org.link.camel.service.PriceService;
import org.link.camel.web.PricePeriodRequest;
import org.link.camel.web.PriceResponse;
import org.link.camel.web.PriceSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean PriceService priceService;
    @MockBean ItemService itemService;

    @Test
    public void currentPriceTest() throws Exception {
        Long productId = 1L;
        PriceSaveRequest request = new PriceSaveRequest(productId, 1000L, 100L);

        when(priceService.getPriceInfo(productId)).thenReturn(request);

        mockMvc.perform(get("/products/{productId}/prices/current", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(1000L))
                .andExpect(jsonPath("$.shipFee").value(100L))
                .andDo(print());
    }

    @Test
    public void productPricesList() throws Exception {
        Long productId = 1L;
        PricePeriodRequest request = new PricePeriodRequest(1);

        Item fakeItem = Item.builder()
                .productId(productId)
                .name("testName")
                .build();

        List<PriceResponse> prices = new ArrayList<>();
        prices.add(new PriceResponse(new Price(null, 1000L, 200L, LocalDateTime.now())));

        when(priceService.searchPriceList(productId, request)).thenReturn(prices);
        when(itemService.getItemInfo(productId)).thenReturn(fakeItem);

        mockMvc.perform(get("/products/{productId}/prices", productId)
                        .param("period", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.name").value("testName"))
                .andExpect(jsonPath("$.prices").isArray())
                .andDo(print());

    }
}