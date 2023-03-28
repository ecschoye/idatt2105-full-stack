package edu.ntnu.idatt2105.ecschoye.backend.service;

import edu.ntnu.idatt2105.ecschoye.backend.controller.UserController;
import edu.ntnu.idatt2105.ecschoye.backend.model.Equation;
import edu.ntnu.idatt2105.ecschoye.backend.model.User;
import edu.ntnu.idatt2105.ecschoye.backend.repository.EquationRepository;
import edu.ntnu.idatt2105.ecschoye.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {
    private UserRepository userRepository;
    private EquationRepository equationRepository;


    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        logger.info("Setting user repository");
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEquationRepository(EquationRepository equationRepository) {
        logger.info("Setting equation repository");
        this.equationRepository = equationRepository;
    }

    public boolean login(String username, String password) {
        logger.info("Received request to login user " + username);
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent()) {
            logger.info("User " + username + " exists. Checking password...");
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        logger.info("User " + username + " does not exist. Returning false");
        return false;
    }

    public boolean getUser(String username) {
        logger.info("Received request to get user " + username);
        return userRepository.findById(username).isPresent();
    }

    public boolean addUser(String username, String password, List<Equation> equations) {
        logger.info("Received request to create user " + username);
        if (userRepository.existsById(username)) {
            logger.info("User " + username + " already exists");
            // Handle the case where the user already exists
            return false;
        }
        logger.info("User " + username + " does not exist. Creating user...");
        User newUser = new User(username, password, equations);
        userRepository.save(newUser);
        return true;
    }

    // Overloaded version of the addUser() method without equations parameter
    public boolean addUser(String username, String password) {
        logger.info("Received request to create user " + username);
        return addUser(username, password, new ArrayList<>());
    }

    public boolean removeUser(String username) {
        logger.info("Received request to remove user " + username);
        if (userRepository.existsById(username)) {
            logger.info("User " + username + " exists. Removing user...");
            userRepository.deleteById(username);
            return true;
        }
        logger.info("User " + username + " does not exist. Returning false");
        return false;
    }
}
