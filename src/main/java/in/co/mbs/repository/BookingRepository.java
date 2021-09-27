package in.co.mbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.mbs.model.Booking;

/**
 * 
 * @author santhosh
 *
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}