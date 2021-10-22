package fr.pierre.goodconscience.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pierre.goodconscience.entity.Categorie;

@Transactional
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

}
