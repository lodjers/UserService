package com.example.UserService.services;

import com.example.UserService.DTO.UserDTO;
import com.example.UserService.models.User;
import com.example.UserService.repositories.UserRepository;
import com.example.UserService.util.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public User findById(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
            throw new UserNotFoundException("Пользователь не найден");
        }
    }
    @Transactional
    public void register(User user) {
        userRepository.save(user);
    }
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

}
