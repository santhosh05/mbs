package in.co.mbs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author santhosh
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "seat_show_id", "seat_number" }) })
public class BookingSeat extends CinemaHallSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private Boolean isReserved;
	@Column(nullable=false)
	private Double price;

	@ManyToOne
	@JoinColumn(name = "seat_booking_id")
	private Booking booking;

	@ManyToOne
	@JoinColumn(name = "seat_show_id", nullable = false)
	private Show show;

	public BookingSeat() {
		super();
	}

	public Boolean getIsReserved() {
		return isReserved;
	}

	public void setIsReserved(Boolean isReserved) {
		this.isReserved = isReserved;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@JsonIgnore
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@JsonIgnore
	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

}
