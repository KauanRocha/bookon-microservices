package br.com.bookon.user.controllers;

import br.com.bookon.user.payloads.request.UserRequest;
import br.com.bookon.user.payloads.response.SimpleUserResponse;
import br.com.bookon.user.payloads.response.UserResponse;
import br.com.bookon.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController implements ApiUserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public UserResponse create(@RequestBody @Valid UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @GetMapping()
    public Boolean existsUser(@RequestParam(name = "userId") Integer userId) {
        return userService.getUser(userId);
    }


}
