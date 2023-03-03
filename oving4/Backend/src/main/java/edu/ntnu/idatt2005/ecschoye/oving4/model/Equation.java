package edu.ntnu.idatt2005.ecschoye.oving4.model;

public class Equation {
    private double operand1;
    private double operand2;
    private String operator;

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s %.2f", operand1, operator, operand2);
    }

}
