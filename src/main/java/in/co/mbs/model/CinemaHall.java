package in.co.mbs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author santhosh
 *
 */
@Entity
public class CinemaHall {

	@Id
	private String name;
	private String displayName;
	private Integer totalSeats;

	@OneToMany(mappedBy = "cinemaHall", cascade = CascadeType.ALL)
	private List<CinemaHallSeat> seats;

	@OneToMany(mappedBy = "playedAt")
	private List<Show> shows;

	@ManyToOne
	@JoinColumn(name = "cinemahall_theater_id", nullable = false)
	private Theater theater;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	@JsonIgnore
	public List<CinemaHallSeat> getSeats() {
		return seats;
	}

	public void setSeats(List<CinemaHallSeat> seats) {
		this.seats = seats;
	}

	@JsonIgnore
	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	@JsonIgnore
	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

}
