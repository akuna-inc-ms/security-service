package com.akuna.security.service;

import com.akuna.security.domain.User;

public interface UserService
{
    void create(User user);

    User get(String id);
}
