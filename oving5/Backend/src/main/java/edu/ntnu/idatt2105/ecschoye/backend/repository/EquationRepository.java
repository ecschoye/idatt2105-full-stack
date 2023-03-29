package edu.ntnu.idatt2105.ecschoye.backend.repository;

import edu.ntnu.idatt2105.ecschoye.backend.model.Equation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquationRepository extends JpaRepository<Equation, Long> {
    List<Equation> findAllByUser_Username(String username);

    List<Equation> findAllByUserUsernameOrderByEquationDesc(String username);

    List<Equation> findAllByUserUsernameOrderByIdDesc(String username);


}
