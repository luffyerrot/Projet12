package fr.pierre.goodconscience.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pierre.goodconscience.entity.UserInformations;

@Transactional
@Repository
public interface UserInformationsRepository extends JpaRepository<UserInformations, Long>{

}
