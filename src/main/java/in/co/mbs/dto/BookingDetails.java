package in.co.mbs.dto;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import in.co.mbs.constraint.MaxSeatConstraint;

/**
 * 
 * @author santhosh
 *
 */
@Configuration
public class BookingDetails {

	@MaxSeatConstraint
	private List<String> seatNumbers;
	private String showId;

	public List<String> getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(List<String> seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}
}
