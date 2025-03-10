package com.example.UserService.controllers;

import com.example.UserService.DTO.UserDTO;
import com.example.UserService.models.User;
import com.example.UserService.services.UserService;
import com.example.UserService.util.ErrorsUtil;
import com.example.UserService.util.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final ModelMapper modelMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }
    @PostMapping("/registration")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDTO userDTO,
                                             BindingResult bindingResult) {
        User user = userService.convertToUser(userDTO);

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
//            ErrorsUtil.returnErrorsToClient(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Некорректные поля клиента");
        }
        userService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Клиент зарегистрирован");
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAll()
                        .stream().map(user -> modelMapper.map(user, UserDTO.class))
                        .collect(Collectors.toList()));
    }

}
