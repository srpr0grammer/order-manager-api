package com.orderapimanager.controller;

import com.orderapimanager.models.Item;
import com.orderapimanager.models.dto.ItemDTO;
import com.orderapimanager.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO dto) {
        var item = itemService.create(modelMapper.map(dto, Item.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(item, ItemDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAll() {
        var body =
                itemService.getAll()
                        .stream().map(entity -> modelMapper.map(entity, ItemDTO.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDTO> getById(@PathVariable Long id) {
        var item = itemService.getById(id);
        var body = modelMapper.map(item, ItemDTO.class);

        return ResponseEntity.ok(body);
    }

    @PutMapping("{id}")
    public ResponseEntity<ItemDTO> update(@PathVariable Long id, @RequestBody ItemDTO dto) {
        dto.setId(id);
        var item = itemService.update(modelMapper.map(dto, Item.class));

        return ResponseEntity.ok(modelMapper.map(item, ItemDTO.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ItemDTO> delete(@PathVariable Long id) {
        itemService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
