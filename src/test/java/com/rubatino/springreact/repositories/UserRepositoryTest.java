package com.rubatino.springreact.repositories;

import com.rubatino.springreact.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Profile("test")
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void checkExistenceOfAnEmail(){
//      Cenário
        User user = createUser();
        entityManager.persist(user);

//      Ação
        boolean result = userRepository.existsByEmail("user@email.com");

//      Verificação
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void returnFalseWhenThereIsNoRecordWithThatEmail(){
//      Cenário

//      Ação
        boolean result = userRepository.existsByEmail("user@email.com");

//      Verificação
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void persistUserInTheDatabase(){
//      Cenário
        User user = createUser();

//      Ação
        User savedUser= userRepository.save(user);

//      Verificação
        Assertions.assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    public void findUserByEmail(){
//      Cenário
        User user = createUser();
        entityManager.persist(user);

//      Ação
        Optional<User> result = userRepository.findByEmail("user@email.com");

//      Verificação
        Assertions.assertThat(result.isPresent()).isTrue();

    }

    @Test
    public void returnEmptyWhenFindUserByEmailWhenItDoesNotExistInDatabase(){
//      Cenário

//      Ação
        Optional<User> result = userRepository.findByEmail("user@email.com");

//      Verificação
        Assertions.assertThat(result.isPresent()).isFalse();
    }

    public static User createUser(){
        return User
                .builder()
                .name("User")
                .email("user@email.com")
                .password("password")
                .build();
    }

}
