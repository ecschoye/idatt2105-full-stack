package edu.ntnu.idatt2105.ecschoye.backend.service;

import edu.ntnu.idatt2105.ecschoye.backend.model.Equation;
import edu.ntnu.idatt2105.ecschoye.backend.model.User;
import edu.ntnu.idatt2105.ecschoye.backend.repository.EquationRepository;
import edu.ntnu.idatt2105.ecschoye.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CalculatorService {

    private static final Logger logger = Logger.getLogger(CalculatorService.class.getName());

    private double result;
    private Equation equation;
    private UserRepository userRepository;
    private EquationRepository equationRepository;
    private String log;


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

    public double calculate(Equation equation) {
        logger.info("Received request to calculate equation " + equation);
        UserService userService = new UserService();
        User username = equation.getUser();
        result = 0;
        String equationString;
        switch (equation.getOperator()) {
            case "+" -> {
                result = equation.getOperand1() + equation.getOperand2();
                equationString = equation.getOperand1() + " + " + equation.getOperand2() + " = " + result;
                log = equationString;
                equation.setEquation(equationString);
                equation.setResult(result);
                Optional<User> userOptional = userRepository.findById(username.getUsername());
                if (userOptional.isPresent()) {
                    logger.info("User " + username + " exists. Checking password...");
                    User user = userOptional.get();
                    equation.setUser(user);
                    equationRepository.save(equation);
                }
                return result;
            }
            case "-" -> {
                result = equation.getOperand1() - equation.getOperand2();
                equationString = equation.getOperand1() + " - " + equation.getOperand2() + " = " + result;
                log = equationString;
                equation.setEquation(equationString);
                equation.setResult(result);
                Optional<User> userOptional = userRepository.findById(username.getUsername());
                if (userOptional.isPresent()) {
                    logger.info("User " + username + " exists. Checking password...");
                    User user = userOptional.get();
                    equation.setUser(user);
                    equationRepository.save(equation);
                }
                return result;
            }
            case "*" -> {
                result = equation.getOperand1() * equation.getOperand2();
                equationString = equation.getOperand1() + " * " + equation.getOperand2() + " = " + result;
                log = equationString;
                equation.setEquation(equationString);
                equation.setResult(result);
                Optional<User> userOptional = userRepository.findById(username.getUsername());
                if (userOptional.isPresent()) {
                    logger.info("User " + username + " exists. Checking password...");
                    User user = userOptional.get();
                    equation.setUser(user);
                    equationRepository.save(equation);
                }
                return result;
            }
            case "/" -> {
                result = equation.getOperand1() / equation.getOperand2();
                equationString = equation.getOperand1() + " / " + equation.getOperand2() + " = " + result;
                log = equationString;
                equation.setEquation(equationString);
                equation.setResult(result);
                Optional<User> userOptional = userRepository.findById(username.getUsername());
                if (userOptional.isPresent()) {
                    logger.info("User " + username + " exists. Checking password...");
                    User user = userOptional.get();
                    equation.setUser(user);
                    equationRepository.save(equation);
                }
                return result;
            }
            default -> {
                logger.severe("Invalid operator: " + equation.getOperator());
                throw new IllegalArgumentException("Invalid operator: " + equation.getOperator());
            }
        }
    }

    public String getLog() {
        return log;
    }

    public List<String> getEquations(String username) {
        List<Equation> equations = equationRepository.findAll();
        Map<String, Integer> userCounts = new HashMap<>();
        return equations.stream()
                .filter(p -> p.getUser().getUsername().equals(username))
                .filter(p -> userCounts.merge(username, 1, Integer::sum) <= 10)
                .map(Equation::getEquation)
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return equation.toString() + result;
    }
}
