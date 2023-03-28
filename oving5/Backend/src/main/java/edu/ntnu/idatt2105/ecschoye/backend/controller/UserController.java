package edu.ntnu.idatt2105.ecschoye.backend.controller;

import com.mysql.cj.log.Log;
import edu.ntnu.idatt2105.ecschoye.backend.model.Equation;
import edu.ntnu.idatt2105.ecschoye.backend.model.LoginRequest;
import edu.ntnu.idatt2105.ecschoye.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @PostMapping("/createuser")
    public boolean createUser(@RequestBody LoginRequest loginRequest) {
        logger.info("Received request to create user " + loginRequest.getUsername());
        return userService.addUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @GetMapping("/getuser")
    public LoginRequest getUser(@RequestParam String username, @RequestParam String password) {
        logger.info("Received request to get user " + username);
        LoginRequest user = new LoginRequest();
        if (userService.getUser(username)) {
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }
        return null;
    }



}
