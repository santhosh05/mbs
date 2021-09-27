package in.co.mbs.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 
 * @author santhosh
 *
 */
public class ShowRequest {

	@NotNull
	private Date startTime;

	@Null
	private Date endTime;
	@NotNull
	@Min(value = 0)
	@Max(value = 500)
	private Integer showCostInRs;
	@NotNull
	private String movie;
	@NotNull
	private String theater;
	@NotNull
	private String cinemaHall;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getShowCostInRs() {
		return showCostInRs;
	}

	public void setShowCostInRs(int showCostInRs) {
		this.showCostInRs = showCostInRs;
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

}
