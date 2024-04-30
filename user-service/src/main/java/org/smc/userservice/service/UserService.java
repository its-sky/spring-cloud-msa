package org.smc.userservice.service;

import org.smc.userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
