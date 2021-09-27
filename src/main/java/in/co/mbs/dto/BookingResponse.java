package in.co.mbs.dto;

/**
 * 
 * @author santhosh
 *
 */
public class BookingResponse {
	private String message;
	private String bookingId;
	private String movie;
	private String theater;
	private String cinemaHall;
	private String seatNumbers;
	private String paidAmount;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public String getCinemaHall() {
		return cinemaHall;
	}

	public void setCinemaHall(String cinemaHall) {
		this.cinemaHall = cinemaHall;
	}

	public String getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

}
