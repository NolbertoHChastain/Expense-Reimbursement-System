package app.ers.repository;

import app.ers.model.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository // 1/4 stereotypes - makes Bean
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}