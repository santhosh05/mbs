package in.co.mbs.rest.controller;

/**
 * 
 */
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.mbs.dto.BookingCheckoutResponse;
import in.co.mbs.dto.BookingDetails;
import in.co.mbs.dto.BookingRequest;
import in.co.mbs.dto.BookingResponse;
import in.co.mbs.model.User;
import in.co.mbs.service.BookingService;

/**
 * booking only allowed for standard user. Booking access restricted for ADMIN
 * 
 * @author santhosh
 * 
 */
@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@PostMapping("/checkout")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<BookingCheckoutResponse> checkout(@AuthenticationPrincipal User user,
			@Valid @RequestBody BookingDetails bookingDetails) {
		BookingCheckoutResponse bookTickets = bookingService.checkout(bookingDetails, user);//
		return new ResponseEntity<>(bookTickets, HttpStatus.CREATED);
	}

	@PostMapping("/payment")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<BookingResponse> confirmTickets(@RequestBody BookingRequest bookingRequest) {
		BookingResponse tickets = bookingService.confirmBooking(bookingRequest.getBookingId());
		return new ResponseEntity<>(tickets, HttpStatus.CREATED);
	}

}
