package com.tekup.staffmanagementapi.user;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    @Override
    public User createUser(User user) {
        user.setEmail(user.getEmail());
        String tempPassword = RandomStringUtils.randomAlphanumeric(8);
        user.setPassword(passwordEncoder.encode(tempPassword));
        user.setRole(Role.EMPLOYEE);
        User savedUser = userRepository.save(user);

        String subject = "Welcome to the Employee Portal";
        String body = " Your temporary password is : " + tempPassword +
                "\nUpdate your profile here : ";
        emailService.sendEmail(savedUser.getEmail(), subject, body);
        return savedUser;
    }

    @Override
    public User updateUser(User updatedUser, Long id) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setPhone(updatedUser.getPhone());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        }).orElseThrow(()-> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
