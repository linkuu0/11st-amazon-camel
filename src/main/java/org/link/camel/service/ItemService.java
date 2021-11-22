package org.link.camel.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.link.camel.api.ProductInfoResponse;
import org.link.camel.config.error.ErrorCode;
import org.link.camel.config.error.exception.EntityNotFoundException;
import org.link.camel.domain.Item;
import org.link.camel.repository.ItemRepository;
import org.link.camel.web.ItemResponse;
import org.link.camel.web.ItemSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ApiService productService;

    @Transactional
    public ItemResponse getItemInfo(Long productId) throws JAXBException {
        Optional<Item> product = itemRepository.findByProductId(productId);

        // 존재하지 않는 경우 데이터를 읽어 저장 후 프레임을 표시해줌
        if (product.isPresent()) {
            return new ItemResponse(product.get());

        } else {
            ProductInfoResponse response = productService.searchProductInfoResponse(productId);
            ItemSaveRequest request = response.toItemSaveRequest();
            Long saveId = saveItem(request);

            return new ItemResponse(itemRepository.findById(saveId).orElseThrow(
                    () -> new EntityNotFoundException(ErrorCode.INTERNAL_SERVER_ERROR)
            ));

        }
    }

    @Transactional(readOnly = true)
    public Long saveItem(ItemSaveRequest request) {
        return itemRepository.save(request.toEntity()).getId();
    }

}
