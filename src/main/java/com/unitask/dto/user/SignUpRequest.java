package com.unitask.dto.user;

import lombok.Data;

@Data
public class SignUpRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String userName;
}
