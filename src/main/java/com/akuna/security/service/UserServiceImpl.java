package com.akuna.security.service;

import com.akuna.security.domain.User;
import com.akuna.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService
{
    private final UserRepository repository;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public void create(User user)
    {
        if (exists(user))
            throw new IllegalArgumentException("user already exists: " + user.getUsername());

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);

        log.info("new user has been created: {}", user.getUsername());
    }


    @Override
    public User get(String id)
    {
        return repository.findById(id).get();
    }


    private boolean exists(User user)
    {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", exact());

        Example<User> example = Example.of(user, matcher);
        return repository.exists(example);
    }
}
