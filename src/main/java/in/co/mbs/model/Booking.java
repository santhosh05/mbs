package in.co.mbs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import in.co.mbs.enums.BookingStatus;

/**
 * 
 * @author santhosh
 *
 */
@Entity
public class Booking {

	@Id
	private Long bookingId;

	@Column(nullable = false)
	private Integer noOfSeats;

	@Column(nullable = false)
	private Date bookedOn;

	@Enumerated(EnumType.STRING)
	private BookingStatus status;

	@ManyToOne
	@JoinColumn(name = "booking_show_id", nullable = false)
	private Show show;

	@OneToMany(mappedBy = "booking")
	private List<BookingSeat> seats;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "bookingId")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "booking_user_id", nullable = false)
	private User user;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public Date getBookedOn() {
		return bookedOn;
	}

	public void setBookedOn(Date bookedOn) {
		this.bookedOn = bookedOn;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public List<BookingSeat> getSeats() {
		return seats;
	}

	public void setSeats(List<BookingSeat> seats) {
		this.seats = seats;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
