package in.co.mbs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import in.co.mbs.dto.BookingCheckoutResponse;
import in.co.mbs.dto.BookingDetails;
import in.co.mbs.dto.BookingResponse;
import in.co.mbs.dto.MessageResponse;
import in.co.mbs.enums.BookingStatus;
import in.co.mbs.enums.PaymentStatus;
import in.co.mbs.enums.ShowStatus;
import in.co.mbs.exceptions.CustomException;
import in.co.mbs.model.Booking;
import in.co.mbs.model.BookingCheckout;
import in.co.mbs.model.BookingSeat;
import in.co.mbs.model.Payment;
import in.co.mbs.model.Show;
import in.co.mbs.model.User;
import in.co.mbs.repository.BookingCheckoutRepository;
import in.co.mbs.repository.BookingRepository;
import in.co.mbs.repository.BookingSeatRepository;
import in.co.mbs.repository.PaymentRepository;
import in.co.mbs.utilities.Utility;

/**
 * 
 * @author santhosh
 *
 */
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	ShowService showService;

	@Autowired
	BookingCheckoutRepository bookingCheckoutRepository;

	@Autowired
	BookingSeatRepository bookingSeatRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	UserService userService;

	@Autowired
	private Environment env;

	@Override
	public BookingCheckoutResponse checkout(BookingDetails bookingDetails, User user) {
		user = userService.getUser(user.getUsername());
		Show show = showService.getShow(bookingDetails.getShowId());
		if (isShowCancelled(show))
			throw new CustomException("Show Cancelled -> [" + show.getShowName() + "]  has been cancelled");

		Date createdTime = new Date();
		List<BookingSeat> bookingSeats = getBookingSeats(show, bookingDetails.getSeatNumbers());

		if (bookingSeats.contains(null) || (bookingSeats.size() != bookingDetails.getSeatNumbers().size())) {
			throw new CustomException("Selected seats not available");
		}

		if (isPreBooked(show, bookingDetails.getSeatNumbers())) {
			throw new CustomException("Seats have been booked already");
		}
		double totalPrice = bookingSeats.stream().mapToDouble(seat -> seat.getPrice()).sum();

		BookingCheckout bookingCheckout = saveBookingCheckout(bookingDetails, createdTime, show, totalPrice, user);
		BookingCheckoutResponse bookingChecoutResponse = getBookingChecoutResponse(createdTime, bookingDetails,
				bookingCheckout.getBookingId(), show, totalPrice, BookingStatus.PAYMENT_PENDING);

		return bookingChecoutResponse;
	}

	private BookingCheckoutResponse getBookingChecoutResponse(Date createdTime, BookingDetails bookingDetails,
			Long bookingId, Show show, Double totalPrice, BookingStatus bookingStatus) {
		BookingCheckoutResponse bookingCheckoutResponse = new BookingCheckoutResponse();
		bookingCheckoutResponse.setBookedOn(createdTime);
		bookingCheckoutResponse.setNoOfSeats(bookingDetails.getSeatNumbers().size());
		bookingCheckoutResponse.setSeats(bookingDetails.getSeatNumbers());
		bookingCheckoutResponse.setBookingId(bookingId);
		bookingCheckoutResponse.setStatus(bookingStatus);
		bookingCheckoutResponse.setPrice(totalPrice);
		bookingCheckoutResponse.setDuration(show.getMovie().getMovieDurationInMins() + " Mins");
		bookingCheckoutResponse.setPlayedAt(
				"At " + show.getPlayedAt().getDisplayName() + " In " + show.getPlayedAt().getTheater().getName());
		bookingCheckoutResponse.setAddress(show.getPlayedAt().getTheater().getAddress());
		bookingCheckoutResponse.setShowName(show.getShowName());
		bookingCheckoutResponse.setStartTime(show.getStartTime());
		return bookingCheckoutResponse;

	}

	private BookingCheckout saveBookingCheckout(BookingDetails bookingDetails, Date createdTime, Show show,
			Double totalPrice, User user) {
		BookingCheckout bookingCheckout = new BookingCheckout();
		bookingCheckout.setSeatNumber(bookingDetails.getSeatNumbers());
		bookingCheckout.setCreatedTime(createdTime);
		bookingCheckout.setShow(show);
		bookingCheckout.setPrice(totalPrice);
		bookingCheckout.setUser(user);
		bookingCheckoutRepository.save(bookingCheckout);
		return bookingCheckout;
	}

	private boolean isPreBooked(Show show, List<String> seatNumbers) {
		for (String seat : seatNumbers) {
			BookingCheckout prebooked = bookingCheckoutRepository.getBookingDetails(show, seat,
					getSeatLockExpiryTime());
			if (prebooked != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public MessageResponse cancelBooking(String bookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingResponse confirmBooking(Long bookingId) {
		BookingCheckout bookingCheckout = bookingCheckoutRepository.getBookingDetails(bookingId,
				getSeatLockExpiryTime());
		if (bookingCheckout == null) {
			throw new CustomException("Booking time expired before payment");
		}
		// isPreBooked(bookingCheckout.getShow(), bookingCheckout.getSeatNumber())
		if (isAlreadyCheckedoutWithMoreSeatsForBooking(bookingCheckout.getSeatNumber(), bookingCheckout)) {
			throw new CustomException("Seats have been booked already");
			// System.err.println("Choosing other customer for booking");
		}

		User user = bookingCheckout.getUser();
		Show show = bookingCheckout.getShow();
		Date bookingTime = bookingCheckout.getCreatedTime();
		List<BookingSeat> bookingSeats = getBookingSeats(show, bookingCheckout.getSeatNumber());
		if(bookingSeats.contains(null))
			throw new CustomException("Seats have been booked already");
		boolean isReserved = bookingSeats.stream().anyMatch(seat->seat.getIsReserved() == true);
		if(isReserved)
			throw new CustomException("Seats have been booked already");
		double totalPrice = bookingSeats.stream().mapToDouble(seat -> seat.getPrice()).sum();

		Booking booking = new Booking();
		Payment payment = getPayment(bookingId, totalPrice, booking);
		if (payment.getPaymentStatus().equals(PaymentStatus.FAILURE)) {
			throw new CustomException("Payment failed for booking id " + bookingId + ". Retry again .....");
		}
		booking.setBookedOn(bookingTime);
		booking.setNoOfSeats(bookingCheckout.getSeatNumber().size());
		booking.setSeats(bookingSeats);
		booking.setShow(show);
		booking.setStatus(BookingStatus.BOOKING_CONFIRMED);
		booking.setBookingId(bookingId);
		booking.setUser(user);
		booking.setPayment(payment);
		bookingRepository.save(booking);
		reserveBookedSeats(bookingSeats, booking);
		BookingResponse bookingResponse = getBookingResponse(bookingId, show, totalPrice, bookingSeats);
		return bookingResponse;
	}

	private void reserveBookedSeats(List<BookingSeat> bookingSeats, Booking booking) {
		for (BookingSeat seat : bookingSeats) {
			seat.setIsReserved(true);
			seat.setBooking(booking);
			bookingSeatRepository.save(seat);
		}

	}

	private boolean isAlreadyCheckedoutWithMoreSeatsForBooking(List<String> seatNumbers,
			BookingCheckout bookingCheckout) {
		for (String seat : seatNumbers) {
			BookingCheckout prebooked = bookingCheckoutRepository.getBookingDetails(bookingCheckout.getShow(), seat,
					getSeatLockExpiryTime());
			if (prebooked != null) {
				if (bookingCheckout.getSeatNumber().size() < prebooked.getSeatNumber().size()) { // other customer
																									// booked more
																									// tickets
					return true;
				}
			}
		}
		return false;
	}

	BookingResponse getBookingResponse(Long bookingId, Show show, double totalPrice, List<BookingSeat> bookingSeats) {
		BookingResponse bookingResponse = new BookingResponse();
		bookingResponse.setBookingId(String.valueOf(bookingId));
		bookingResponse.setCinemaHall(show.getPlayedAt().getDisplayName());
		bookingResponse.setMessage("Booking Confirmed");
		bookingResponse.setPaidAmount("\u20B9 " + String.valueOf(totalPrice));
		bookingResponse.setSeatNumbers(
				bookingSeats.stream().map(seat -> seat.getSeatNumber()).collect(Collectors.joining(",")));
		bookingResponse.setMovie(show.getMovie().getTitle());
		bookingResponse.setTheater(show.getPlayedAt().getTheater().getName());
		return bookingResponse;
	}

	private Payment getPayment(Long bookingId, double price, Booking booking) {
		Payment payment = new Payment();
		payment.setPaymentId(Utility.getInstance().getPaymentId(bookingId));
		payment.setPaymentStatus(Utility.getInstance().getPaymentStatus());
		payment.setTotalAmount(price);
		payment.setBookingId(booking);
		return payment;
	}

	private Date getSeatLockExpiryTime() {
		return Utility.getInstance().reduceMinutesToDate(new Date(),
				Integer.valueOf(env.getProperty("seat.locking.timeInMinutes")));
	}

	private boolean isShowCancelled(Show show) {
		return show.getStatus().equals(ShowStatus.CANCELLED);
	}

	private List<BookingSeat> getBookingSeats(Show show, List<String> seatNumbers) {
		List<BookingSeat> bookingSeats = new ArrayList<>();
		for (String seatNumber : seatNumbers) {
			BookingSeat bookingSeat = bookingSeatRepository.getSeatByShowIdAndSeatNumber(show, seatNumber);
			bookingSeats.add(bookingSeat);
		}
		return bookingSeats;
	}
}
