package com.app.AppJava.services;

import com.app.AppJava.models.User;
import com.app.AppJava.payload.response.MessageResponse;
import com.app.AppJava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> allUsers() {
        return  userRepository.findAll();
    }

    public Optional<User> oneUser(Long id) {
        return userRepository.findById(id);
    }

    public MessageResponse createUserValidation(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            return new MessageResponse("Username already exist");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return new MessageResponse("Email already exist");
        }

        if (user.getUsername().isEmpty()) {
            return new MessageResponse("Username can't be empty");
        }

        if (user.getEmail().isEmpty()) {
            return new MessageResponse("Email can't be empty");
        }

        if (user.getPassword().isEmpty()) {
            return new MessageResponse("Password can't be empty");
        }

        if (user.getRoles().isEmpty()) {
            return new MessageResponse("Roles can't be empty");
        }


        return new MessageResponse("Ok");
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user, Long id) {
        Optional<User> updatingUser = userRepository.findById(id);

        if (updatingUser.isPresent()) {
            User newUpdatedUser = updatingUser.get();

            if (!userRepository.existsByEmail(user.getEmail())) {
                newUpdatedUser.setEmail(user.getEmail());

            }

            if (!userRepository.existsByUsername(user.getUsername())) {
                newUpdatedUser.setUsername(user.getUsername());

            }

            newUpdatedUser.setPassword(user.getPassword());
            newUpdatedUser.setRoles(user.getRoles());


            userRepository.save(newUpdatedUser);
        }
    }

    public void deleteUser(Long id) {
        Optional<User> deletingUser = userRepository.findById(id);

        deletingUser.ifPresent(user -> userRepository.delete(user));
    }
}
