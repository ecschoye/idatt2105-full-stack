package edu.ntnu.idatt2005.ecschoye.oving4.controller;

import edu.ntnu.idatt2005.ecschoye.oving4.model.Equation;
import edu.ntnu.idatt2005.ecschoye.oving4.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class CalculatorController {

    private static final Logger logger = Logger.getLogger(CalculatorController.class.getName());

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public String calculateEquation(@RequestBody Equation equation) {
        logger.info("Received request to calculate equation: " + equation.toString());
        double result = calculatorService.calculate(equation);
        logger.info("Calculated equation: " + equation + " = " + result);
        System.out.println("Calculated equation: " + equation+ " = " + result);
        return Double.toString(result);
    }
}
