package fr.pierre.goodconscience.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pierre.goodconscience.entity.User;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}
