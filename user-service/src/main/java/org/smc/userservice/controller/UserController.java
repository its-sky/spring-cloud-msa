package org.smc.userservice.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.smc.userservice.dto.request.UserDto;
import org.smc.userservice.dto.response.ResponseUser;
import org.smc.userservice.model.UserEntity;
import org.smc.userservice.service.UserService;
import org.smc.userservice.vo.RequestUser;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {

    private final Environment env;
    private final UserService userService;

    @GetMapping("/health_check")
    public String healthCheck() {
        return String.format("It's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody RequestUser requestUser) {
        ModelMapper mapper = new ModelMapper();
        UserDto dto = mapper.map(requestUser, UserDto.class);
        userService.createUser(dto);

        return ResponseEntity.created(URI.create("users/" + dto.getUserId().toString())).build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(user -> {
            result.add(new ModelMapper().map(user, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);

        ResponseUser response = new ModelMapper().map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
