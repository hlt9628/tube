package com.pty.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pty.domain.User;
import com.pty.service.UserService;
import com.pty.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 16539
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-03-27 21:33:09
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




