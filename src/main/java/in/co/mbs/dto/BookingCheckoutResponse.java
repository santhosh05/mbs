package in.co.mbs.dto;

import java.util.Date;
import java.util.List;

import in.co.mbs.enums.BookingStatus;
import in.co.mbs.model.Address;
import in.co.mbs.model.Show;

/**
 * 
 * @author santhosh
 *
 */
public class BookingCheckoutResponse {

	private Long bookingId;
	private Integer noOfSeats;
	private Date bookedOn;
	private BookingStatus status;
	private Show show;
	private String showName;
	private Date startTime;
	private String duration;
	private String playedAt;
	private Address address;
	private List<String> seats;
	private Double price;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
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

	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date date) {
		this.startTime = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPlayedAt() {
		return playedAt;
	}

	public void setPlayedAt(String playedAt) {
		this.playedAt = playedAt;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
