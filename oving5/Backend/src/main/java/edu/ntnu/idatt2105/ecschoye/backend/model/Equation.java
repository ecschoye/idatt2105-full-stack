package edu.ntnu.idatt2105.ecschoye.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.ntnu.idatt2105.ecschoye.backend.repository.UserRepository;
import edu.ntnu.idatt2105.ecschoye.backend.service.UserService;
import jakarta.persistence.*;

@Entity
@Table(name = "equations")
public class Equation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double operand1;
    private String operator;
    private double operand2;
    private String equation;
    private double result;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Equation() {}

    public Equation(double operand1, String operator, double operand2, String equation, double result, User user) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
        this.equation = equation;
        this.result = result;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return getUser().getUsername();
    }

    /*
    @Override
    public String toString() {
        return String.format("%.2f %s %.2f", operand1, operator, operand2);
    }
    */

    @Override
    public String toString() {
        return "Equation{" +
                "id=" + id +
                ", operand1=" + operand1 +
                ", operator='" + operator + '\'' +
                ", operand2=" + operand2 +
                ", equation='" + equation + '\'' +
                ", result=" + result +
                ", user=" + user.getUsername() +
                '}';
    }
}
