package fr.pierre.goodconscience.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pierre.goodconscience.entity.Role;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
