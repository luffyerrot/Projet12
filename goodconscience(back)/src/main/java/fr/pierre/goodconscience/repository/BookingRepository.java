package fr.pierre.goodconscience.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pierre.goodconscience.entity.Booking;

@Transactional
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	public List<Booking> findByUserId(Long id);
	public List<Booking> findByGiftBasketName(String name);
 	
	public Booking findByGiftBasketId(Long id);
}
