package org.smc.userservice.dto.request;

import java.util.Date;
import java.util.List;

import org.smc.userservice.dto.response.ResponseOrder;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;

    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
