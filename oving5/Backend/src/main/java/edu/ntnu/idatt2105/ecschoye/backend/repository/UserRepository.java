package edu.ntnu.idatt2105.ecschoye.backend.repository;

import edu.ntnu.idatt2105.ecschoye.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Override
    Optional<User> findById(String s);

    User findByUsernameAndPassword(String username, String password);

    @Override
    <S extends User> S save(S s);


}
