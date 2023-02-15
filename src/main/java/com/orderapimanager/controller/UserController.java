package com.orderapimanager.controller;

import com.orderapimanager.models.User;
import com.orderapimanager.models.dto.UserDTO;
import com.orderapimanager.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        var user = userService.create(modelMapper.map(dto, User.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(user, UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        var body =
                userService.getAll()
                        .stream().map(entity -> modelMapper.map(entity, UserDTO.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        var user = userService.getById(id);
        var body = modelMapper.map(user, UserDTO.class);

        return ResponseEntity.ok(body);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        dto.setId(id);
        var user = userService.update(modelMapper.map(dto, User.class));

        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
