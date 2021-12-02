package com.koreait.basic.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class LoginResult {
    private final int result;
    private final UserEntity loginUser;

}
