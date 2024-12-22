package app.ers.service;

import java.util.List;
import java.util.Optional;

import app.ers.exception.UnauthorizedException;
import app.ers.model.DTO.IncomingUserDTO;
import app.ers.model.User;
import app.ers.exception.RepositoryException;
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
            } else throw new RepositoryException("An account with that username already exists");
        } else throw new InvalidRegistrationException("Invalid account details");
    }

    public List<User> getAllUsers(int userId) {
        // 1. ensure user exists
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            // 2. ensure user is manager
            if (user.get().getRole().equals("manager")) {
                List<User> users = userRepository.findAll();

                for (User existingUser : users) {
                    existingUser.setUsername(null);
                    existingUser.setPassword(null);
                } // for each : existing user nullify username & password
                return users;
            } throw new UnauthorizedException("not authorized to perform this action");
        } throw new RepositoryException("invalid user details");
    }

    private boolean validFields(User user) {
        return !(user.getFirstname().isBlank() || user.getLastname().isBlank() || user.getUsername().isBlank() ||
                user.getPassword().isBlank() || user.getRole().isBlank());
    }
}