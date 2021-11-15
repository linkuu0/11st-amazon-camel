package org.link.camel.service;


import lombok.RequiredArgsConstructor;
import org.link.camel.domain.Item;
import org.link.camel.repository.ItemRepository;
import org.link.camel.web.ChartResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public String getItemById(Long id) {
        Item item = itemRepository.getById(id);
        return null;
    }

    @Transactional(readOnly = true)
    public ChartResponse getChartData(Long id) {
        return null;
    }


}
