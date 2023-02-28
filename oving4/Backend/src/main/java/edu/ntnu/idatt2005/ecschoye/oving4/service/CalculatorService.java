package edu.ntnu.idatt2005.ecschoye.oving4.service;

import edu.ntnu.idatt2005.ecschoye.oving4.model.Equation;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CalculatorService {

    private static final Logger logger = Logger.getLogger(CalculatorService.class.getName());

    private double result;
    private Equation equation;

    public double calculate(Equation equation) {
        result = 0;
        switch (equation.getOperator()) {
            case "+" -> result = equation.getOperand1() + equation.getOperand2();
            case "-" -> result = equation.getOperand1() - equation.getOperand2();
            case "*" -> result = equation.getOperand1() * equation.getOperand2();
            case "/" -> result = equation.getOperand1() / equation.getOperand2();
            default -> {
                logger.severe("Invalid operator: " + equation.getOperator());
                throw new IllegalArgumentException("Invalid operator: " + equation.getOperator());
            }
        }
        logger.info("Calculated " + equation.toString() + " = " + result);
        return result;
    }

    public double getResult() {
        return result;
    }

    @Override
    public String toString() {
        return equation.toString() + result;
    }
}
