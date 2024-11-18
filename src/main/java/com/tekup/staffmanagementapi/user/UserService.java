package com.tekup.staffmanagementapi.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    User updateUser(User updatedUser, Long id);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void deleteUserById(Long id);
}
