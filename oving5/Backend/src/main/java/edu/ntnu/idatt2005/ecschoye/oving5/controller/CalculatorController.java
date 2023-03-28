package edu.ntnu.idatt2005.ecschoye.oving5.controller;

import edu.ntnu.idatt2005.ecschoye.oving5.model.Equation;
import edu.ntnu.idatt2005.ecschoye.oving5.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.logging.Logger;

/**

 The CalculatorController class is responsible for handling HTTP requests related to calculating equations.
 It receives an Equation object as the request body and returns the result of the calculation as a String.
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class CalculatorController {

    private static final Logger logger = Logger.getLogger(CalculatorController.class.getName());

    private final CalculatorService calculatorService;


    /**
     * Constructor that injects a CalculatorService instance into the Controller
     * @param calculatorService - the CalculatorService instance to be injected
     */
    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /**
     * HTTP POST endpoint for calculating equations.
     * @param equation - the Equation object representing the equation to be calculated
     * @return the result of the calculation as a String
     */
    @PostMapping("/calculate")
    public String calculateEquation(@RequestBody Equation equation) {
        logger.info("Received request to calculate equation: " + equation.toString());
        double result = calculatorService.calculate(equation);
        logger.info("Calculated equation: " + equation + " = " + result);
        return Double.toString(result);
    }
}
