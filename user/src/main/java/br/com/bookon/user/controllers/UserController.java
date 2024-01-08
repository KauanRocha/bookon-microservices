package br.com.bookon.user.controllers;

import br.com.bookon.user.payloads.request.UserRequest;
import br.com.bookon.user.payloads.response.SimpleUserResponse;
import br.com.bookon.user.payloads.response.UserResponse;
import br.com.bookon.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController implements ApiUserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping

    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest) {
        return new ResponseEntity<>(userService.create(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SimpleUserResponse> getOne(@PathVariable(value = "userId") Integer userId) {
        return new ResponseEntity<>( userService.getUser(userId), HttpStatus.OK);
    }
}
