package br.com.bookon.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bookon.server.payload.request.postgres.FilterRequest;
import br.com.bookon.server.payload.response.postgres.UserResponse;
import br.com.bookon.server.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/search")
    public Page<UserResponse> search(@RequestBody FilterRequest filterRequest) {
        return userService.list(filterRequest).map(UserResponse::new);
    }
    
}
