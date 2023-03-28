package edu.ntnu.idatt2105.ecschoye.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Equation> equations;


    public User(String username, String password, List<Equation> equations) {
        this.username = username;
        this.password = password;
        this.equations = equations;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Equation> getEquations() {
        return equations;
    }

    public void setEquations(List<Equation> equations) {
        this.equations = equations;
    }

    public void addEquation(Equation equation) {
        if (!this.equations.contains(equation))
            this.equations.add(equation);
    }

    public void removeEquation(Equation equation) {
        this.equations.removeIf(e -> e.equals(equation));
    }
}
