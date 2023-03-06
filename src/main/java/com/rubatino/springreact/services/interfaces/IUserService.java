package com.rubatino.springreact.services.interfaces;

import com.rubatino.springreact.entities.User;

public interface IUserService {

    User authenticate(String email, String password);
    User register(User user);
    void emailValidate(String email);

}
