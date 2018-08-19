package com.edu.system.repository;

import com.edu.system.model.user.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Integer> {
    Optional<User> findByLogin(String login);
    Optional<User> findByAccessCodeAndPasswordIsNull(String accessCode);
}
