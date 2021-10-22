package fr.pierre.goodconscience.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pierre.goodconscience.entity.Product;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByEnterpriseId(Long id);
}
