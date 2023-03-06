package com.rubatino.springreact.services;

import com.rubatino.springreact.entities.User;
import com.rubatino.springreact.exceptions.AuthenticationError;
import com.rubatino.springreact.exceptions.BusinessRuleException;
import com.rubatino.springreact.repositories.UserRepository;
import com.rubatino.springreact.services.interfaces.IUserService;
import org.assertj.core.api.Assertions;
import org.h2.security.auth.AuthenticationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Profile("test")
@ActiveProfiles("test")
public class UserServiceTest {

    IUserService userService;

    @MockBean
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        userService = new UserService(userRepository);
    }

    @Test
    public void validateEmail(){
        //Cenário
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

        //Ação
        userService.emailValidate("email@email.com");

        //Verificação

    }

    @Test
    public void throwExceptionWhenValidatingEmailWhenThereIsARegisteredUser(){
        //Cenário
        Mockito
                .when(userRepository.existsByEmail(Mockito.anyString()))
                .thenReturn(true);

        //Ação
        UserService us = Mockito.mock(UserService.class);

        //Verificação
        Mockito.doThrow(BusinessRuleException.class)
                .when(us)
                .emailValidate(Mockito.anyString());

    }

    @Test
    public void successfullyAuthenticateUser(){
        //Cenário
        String email = "user@email.com";
        String password = "password";

        User user = User
                .builder()
                .id(1L)
                .name("User")
                .email(email)
                .password(password)
                .build();
        Mockito
                .when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));


        //Ação
        User result = userService.authenticate(email, password);

        //Verificação
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void throwExceptionWhenNotFoundRegisteredUserWithInformedEmail(){
        //Cenário
        Mockito
                .when(userRepository.findByEmail(Mockito.anyString()))
                .thenReturn(Optional.empty());

        //Ação
        UserService us = Mockito.mock(UserService.class);

        //Verificação
        Mockito
                .doThrow(AuthenticationError.class)
                .when(us)
                .authenticate("user@email.com", "password");
    }

    @Test
    public void

}
