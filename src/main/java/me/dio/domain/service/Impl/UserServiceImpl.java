package me.dio.domain.service.Impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.domain.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public User update(User userToUpdate) {
        if (!userRepository.existsById(userToUpdate.getId())) {
            throw new IllegalArgumentException("User not found.");
        }
        return userRepository.save(userToUpdate);
    }

    @Override
    public User delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        
        if (!user.isPresent()) {
            throw new IllegalArgumentException("This ID does not exist.");
        }
        
        userRepository.deleteById(id);
        return user.get(); 
    }

}
