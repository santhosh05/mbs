package in.co.mbs.service;

import org.springframework.stereotype.Component;

import in.co.mbs.dto.BookingCheckoutResponse;
import in.co.mbs.dto.BookingDetails;
import in.co.mbs.dto.BookingResponse;
import in.co.mbs.dto.MessageResponse;
import in.co.mbs.model.User;

/**
 * 
 * @author santhosh
 *
 */
@Component
public interface BookingService {

	public BookingResponse confirmBooking(Long bookingId);

	public MessageResponse cancelBooking(String bookingId);

	public BookingCheckoutResponse checkout(BookingDetails bookingDetails, User user);

}
