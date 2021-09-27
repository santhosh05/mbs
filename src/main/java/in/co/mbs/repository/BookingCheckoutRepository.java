package in.co.mbs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.co.mbs.model.BookingCheckout;
import in.co.mbs.model.Show;

/**
 * 
 * @author santhosh
 *
 */
@Repository
public interface BookingCheckoutRepository extends JpaRepository<BookingCheckout, Long> {

	@Query("select bc from BookingCheckout bc where bc.bookingId=?1 and bc.createdTime >= ?2")
	public BookingCheckout getBookingDetails(Long bookingId, Date createdTime);

	@Query("select bc from BookingCheckout bc  where bc.show=?1 and ?2 in elements(bc.seatNumber) and bc.createdTime >= ?3 and bc.show = (select bs.show from BookingSeat bs where bs.isReserved= false and bs.show=?1 and bs.seatNumber=?2 )")
	public BookingCheckout getBookingDetails(Show show, String seatNumbers, Date createdTime);

	@Query("delete from BookingCheckout bc where bc.bookingId=?1")
	public List<BookingCheckout> deleteByBookingId(Long bookingId);

}