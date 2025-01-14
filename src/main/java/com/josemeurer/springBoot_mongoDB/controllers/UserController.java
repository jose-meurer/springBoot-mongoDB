package com.josemeurer.springBoot_mongoDB.controllers;

import com.josemeurer.springBoot_mongoDB.dtos.UserDTO;
import com.josemeurer.springBoot_mongoDB.dtos.UserInsertDTO;
import com.josemeurer.springBoot_mongoDB.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private HttpServletRequest req;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        UserDTO dto = userService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserInsertDTO dto) throws URISyntaxException {
        UserDTO userDto = userService.insert(dto);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDto.getId())
                .toUri();

//        URI uri1 = new URI(req.getRequestURL().toString() + "/" + userDto.getId());
        return ResponseEntity.created(uri).build();
    }
}
