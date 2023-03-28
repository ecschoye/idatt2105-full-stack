package edu.ntnu.idatt2105.ecschoye.backend.controller;

import edu.ntnu.idatt2105.ecschoye.backend.model.Equation;
import edu.ntnu.idatt2105.ecschoye.backend.service.CalculatorService;
import edu.ntnu.idatt2105.ecschoye.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.logging.Logger;

/**

 The CalculatorController class is responsible for handling HTTP requests related to calculating equations.
 It receives an Equation object as the request body and returns the result of the calculation as a String.
 */

@RestController
@RequestMapping("/calculator")
@CrossOrigin(origins = "http://localhost:8081")
public class CalculatorController {

    private static final Logger logger = Logger.getLogger(CalculatorController.class.getName());

    @Autowired
    private final CalculatorService calculatorService;


    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping(value = "/", produces = "text/html")
    public String index() {
        return "CalculatorController is up and running!";
    }

    /**
     * HTTP POST endpoint for calculating equations.
     * @param equation - the Equation object representing the equation to be calculated
     * @return the result of the calculation as a String
     */
    @PostMapping("/calculate")
    public String calculateEquation(@RequestBody Equation equation) {
        logger.info("Received request to calculate equation: " + equation.toString());
        logger.info(equation.toString());
        double result = calculatorService.calculate(equation);
        logger.info("Calculated equation: " + equation + " = " + result);
        return Double.toString(result);
    }

    @PostMapping("getLog")
    public String getLog(@RequestBody Equation equation) {
        logger.info("Received request to get log: " + equation.toString());
        String log = calculatorService.getLog();
        logger.info("Log: " + log);
        return log;
    }

    @PostMapping("getCalculations")
    public List<String> getCalculations(@RequestBody Equation equation) {
        logger.info("Received request to get calculations: " + equation.toString());
        String username = equation.getUsername();
        return calculatorService.getEquations(username);
    }
}
