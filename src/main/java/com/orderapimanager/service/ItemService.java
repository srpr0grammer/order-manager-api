package com.orderapimanager.service;

import com.orderapimanager.models.Item;
import com.orderapimanager.repository.ItemRepository;
import com.orderapimanager.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
                .orElseThrow(() -> new ObjectNotFoundException("Item not found"));
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
