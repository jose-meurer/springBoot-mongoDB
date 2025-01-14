package com.josemeurer.springBoot_mongoDB.services;

import com.josemeurer.springBoot_mongoDB.dtos.UserDTO;
import com.josemeurer.springBoot_mongoDB.entities.User;
import com.josemeurer.springBoot_mongoDB.exceptions.ObjectNotFoundException;
import com.josemeurer.springBoot_mongoDB.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        return new UserDTO(user);
    }
}
