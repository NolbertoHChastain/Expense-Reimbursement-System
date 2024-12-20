package app.ers.service;

import java.util.Optional;

import app.ers.model.DTO.IncomingUserDTO;
import app.ers.model.User;
import app.ers.exception.DuplicateUsernameException;
import app.ers.exception.InvalidRegistrationException;
import app.ers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    } // constructor injection

    // username, password, role
    public User register(IncomingUserDTO userDTO) {
        // 1. convert userDTO to  User model class
        User user = new User(0, userDTO.getFirstname(), userDTO.getLastname(),
                userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole());

        // 2. verify user fields are valid
        if (validFields(user)) {
            // 3. verify user does not already exist
            Optional<User> existingUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
            if (!existingUser.isPresent()) {
                return userRepository.save(user);
            } else throw new DuplicateUsernameException("An account with that username already exists");
        } else throw new InvalidRegistrationException("Invalid account details");
    }

    private boolean validFields(User user) {
        return !(user.getFirstname().isBlank() || user.getLastname().isBlank() || user.getUsername().isBlank() ||
                user.getPassword().isBlank() || user.getRole().isBlank());
    }
}