package br.com.bookon.user.services;

import br.com.bookon.user.exceptions.UserNotFoundException;
import br.com.bookon.user.model.User;
import br.com.bookon.user.payloads.request.UserRequest;
import br.com.bookon.user.payloads.response.SimpleUserResponse;
import br.com.bookon.user.payloads.response.UserResponse;
import br.com.bookon.user.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public UserResponse create(UserRequest userRequest) {
        var newUser = new User();
        BeanUtils.copyProperties(userRequest, newUser);
        save(newUser);

        return new UserResponse(newUser.getId(), newUser.getName());
    }

    public Boolean getUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user-not-found"));

        return Boolean.TRUE;
    }
}
