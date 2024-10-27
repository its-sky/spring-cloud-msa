package org.smc.userservice.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.smc.userservice.dto.UserDto;
import org.smc.userservice.service.UserService;
import org.smc.userservice.vo.RequestUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Value("${greeting.message}")
    private String greetingMessage;

    @GetMapping("health_check")
    public String healthCheck() {
        return greetingMessage;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody RequestUser requestUser) {
        ModelMapper mapper = new ModelMapper();
        UserDto dto = mapper.map(requestUser, UserDto.class);
        userService.createUser(dto);

        return ResponseEntity.created(URI.create("users/" + dto.getUserId().toString())).build();
    }

}
