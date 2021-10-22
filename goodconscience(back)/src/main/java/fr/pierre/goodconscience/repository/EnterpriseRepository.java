package fr.pierre.goodconscience.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pierre.goodconscience.entity.Enterprise;

@Transactional
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long>{

	Optional<Enterprise> findByEmail(String email);
}
