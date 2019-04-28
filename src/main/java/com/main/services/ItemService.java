package com.main.services;

import com.main.persistence.domain.Item;
import com.main.persistence.repository.ItemRepository;
import com.main.vo.BaseVO;
import com.main.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public BaseVO createItem(ItemVO itemVO){
        Item item = new Item();
        item.setName(itemVO.getName());
        item.setStock(itemVO.getStock());
        item.setDescription(itemVO.getDescription());
        item = itemRepository.save(item);
        return new BaseVO(item.getSecureId(),item.getVersion());
    }
}
