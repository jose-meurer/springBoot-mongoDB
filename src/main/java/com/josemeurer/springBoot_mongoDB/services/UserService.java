package com.josemeurer.springBoot_mongoDB.services;

import com.josemeurer.springBoot_mongoDB.dtos.UserDTO;
import com.josemeurer.springBoot_mongoDB.dtos.UserInsertDTO;
import com.josemeurer.springBoot_mongoDB.dtos.UserUpdateDTO;
import com.josemeurer.springBoot_mongoDB.entities.User;
import com.josemeurer.springBoot_mongoDB.services.exceptions.ObjectNotFoundException;
import com.josemeurer.springBoot_mongoDB.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User user = new User(null, dto.getName(), dto.getEmail());
        user = userRepository.insert(user);
        return new UserDTO(user);
    }

    @Transactional
    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new ObjectNotFoundException("Id not found");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDTO update(String id, UserUpdateDTO dto) {
        if (!userRepository.existsById(id)) {
            throw new ObjectNotFoundException("Id not found");
        }
        User user = new User(id, dto.getName(), dto.getEmail());
        user = userRepository.save(user);
        return new UserDTO(user);
    }
}
