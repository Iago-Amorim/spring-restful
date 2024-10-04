package me.dio.domain.service;

import java.util.List;

import me.dio.domain.model.User;

public interface UserService {

    User findById(Long id);
    
    List<User> findAll();

    User create(User userToCreate);
    
    User update(User userToUpdate);

    User delete(Long id);
}
