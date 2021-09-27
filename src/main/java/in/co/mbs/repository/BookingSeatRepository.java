package in.co.mbs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.co.mbs.model.BookingSeat;
import in.co.mbs.model.Show;

/**
 * 
 * @author santhosh
 *
 */
@Repository
public interface BookingSeatRepository extends JpaRepository<BookingSeat, String> {

	@Query(value = "select b from BookingSeat b where b.show = ?1 and b.isReserved=false and b.seatNumber not in (select elements(bc.seatNumber) from BookingCheckout bc where bc.show=?1 and bc.createdTime >= ?2)")
	List<BookingSeat> findAllAvailableSeats(Show show, Date createdTime);

	@Query(value = "select b from BookingSeat b where b.show = ?1 and b.isReserved=false and b.seatNumber = ?2")
	public BookingSeat getSeatByShowIdAndSeatNumber(Show show, String seatNumber);

	@Query(value = "select b from BookingSeat b where b.show = ?1 and b.isReserved=false")
	public List<BookingSeat> getSeatByShow(Show show);

}