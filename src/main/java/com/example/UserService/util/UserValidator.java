package com.example.UserService.util;

import com.example.UserService.models.User;
import com.example.UserService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {
    private final UserService userService;


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userService.findByName(user.getName()).isPresent()) {
            errors.rejectValue("name", "Человек с таким именем уже есть");
        }
    }
}
