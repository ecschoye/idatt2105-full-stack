package edu.ntnu.idatt2005.ecschoye.oving4.model;

public class Equation {
    private double operand1;
    private double operand2;
    private String operator;

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s %.2f", operand1, operator, operand2);
    }

}
