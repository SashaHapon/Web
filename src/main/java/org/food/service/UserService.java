package org.food.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.food.security.model.Role;
import org.food.security.model.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final List<User> users;

    public UserService() {
        this.users = List.of(
                new User("anton", "1234", "Антон", "Иванов", 5, "sfdf", Collections.singleton(Role.USER)),
                new User("ivan", "12345", "Сергей", "Петров", 5, "dfsf", Collections.singleton(Role.ADMIN))
        );
    }

    public Optional<User> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

}