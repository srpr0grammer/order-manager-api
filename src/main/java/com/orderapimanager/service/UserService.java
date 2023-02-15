package com.orderapimanager.service;


import com.orderapimanager.models.User;
import com.orderapimanager.repository.UserRepository;
import com.orderapimanager.service.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User not found", HttpStatus.NOT_FOUND.value()));
    }
    public User update (User item) {
        getById(item.getId());
        return userRepository.save(item);
    }

    public void delete (Long id) {
        getById(id);
        userRepository.deleteById(id);
    }

}
