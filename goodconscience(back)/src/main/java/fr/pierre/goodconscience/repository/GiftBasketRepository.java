package fr.pierre.goodconscience.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pierre.goodconscience.entity.GiftBasket;

@Transactional
@Repository
public interface GiftBasketRepository extends JpaRepository<GiftBasket, Long>{

	public List<GiftBasket> findByEnterpriseId(Long id);
	
	public List<GiftBasket> findByName(String name);
}
