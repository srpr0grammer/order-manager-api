package com.orderapimanager.service;

import com.orderapimanager.models.Item;
import com.orderapimanager.repository.ItemRepository;
import com.orderapimanager.service.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }
    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Item not found", HttpStatus.NOT_FOUND.value()));
    }
    public Item update (Item item) {
        getById(item.getId());
        return itemRepository.save(item);
    }

    public void delete (Long id) {
        getById(id);
        itemRepository.deleteById(id);
    }


}
