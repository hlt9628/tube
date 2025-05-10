package com.pty.service;

import com.pty.domain.ResponseResult;
import com.pty.domain.User;

public interface LoginServcie {
    ResponseResult login(User user);

    ResponseResult logout();

}
