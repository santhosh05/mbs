package in.co.mbs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import in.co.mbs.generator.CustomBookingIdGenerator;

/**
 * 
 * @author santhosh
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "booking_checkout_show_id", "booking_id" }) })
public class BookingCheckout {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "booking_checkout_show_id", nullable = false)
	private Show show;

	@GeneratorType(type = CustomBookingIdGenerator.class, when = GenerationTime.INSERT)
	@Column(name = "booking_id", unique = true, nullable = false, columnDefinition = "serial")
	private Long bookingId;

	@ElementCollection
	@Column(name = "seat_number", nullable = false)
	private List<String> seatNumber = new ArrayList<String>();

	@Column(nullable = false)
	private Date createdTime;
	@Column(nullable = false)
	private Double price;

	@ManyToOne
	@JoinColumn(name = "bookingcheckout_user_id", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public List<String> getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(List<String> seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
