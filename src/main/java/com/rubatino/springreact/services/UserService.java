package com.rubatino.springreact.services;

import com.rubatino.springreact.entities.User;
import com.rubatino.springreact.exceptions.AuthenticationError;
import com.rubatino.springreact.exceptions.BusinessRuleException;
import com.rubatino.springreact.repositories.UserRepository;
import com.rubatino.springreact.services.interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new AuthenticationError("User not found.");
        }
        if(!user.get().getPassword().equals(password)){
            throw new AuthenticationError("Invalid password.");
        }
        return user.get();
    }

    @Override
    @Transactional
    public User register(User user) {
        emailValidate(user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void emailValidate(String email) {
        boolean exist = userRepository.existsByEmail(email);
        if(exist){
            throw new BusinessRuleException("There is already a registered user with this email");
        }
    }
}
