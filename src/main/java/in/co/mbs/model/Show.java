package in.co.mbs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.co.mbs.enums.ShowStatus;

/**
 * 
 * @author santhosh
 *
 */
@Entity
@Table(name = "shows")
public class Show {

	@Id
	private String showName;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date timeAdded;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date startTime;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date endTime;

	@ManyToOne
	@JoinColumn(name = "show_cinemahall_id", nullable = false)
	private CinemaHall playedAt;

	@ManyToOne
	@JoinColumn(name = "show_movie_id", nullable = false)
	private Movie movie;

	@OneToMany(mappedBy = "show")
	private List<Booking> bookings;

	@Enumerated(value = EnumType.STRING)
	private ShowStatus status;

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Date getTimeAdded() {
		return timeAdded;
	}

	public void setTimeAdded(Date timeAdded) {
		this.timeAdded = timeAdded;
	}

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

	public CinemaHall getPlayedAt() {
		return playedAt;
	}

	public void setPlayedAt(CinemaHall playedAt) {
		this.playedAt = playedAt;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@JsonIgnore
	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public ShowStatus getStatus() {
		return status;
	}

	public void setStatus(ShowStatus status) {
		this.status = status;
	}

}
