package br.com.bookon.user.controllers;

import br.com.bookon.user.payloads.request.UserRequest;
import br.com.bookon.user.payloads.response.SimplesUserResponse;
import br.com.bookon.user.payloads.response.UserResponse;
import br.com.bookon.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements ApiUserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public UserResponse create(@Valid UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @GetMapping
    public SimplesUserResponse getUser(@Valid Integer userId) {
        return userService.getUser(userId);
    }
}
