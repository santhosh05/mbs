package in.co.mbs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author santhosh
 *
 */
@Entity
public class Movie {

	@Id
	private String title;
	
	@Column(nullable = false)
	private int movieDurationInMins;
	private String description;
	
	@Column(nullable = false)
	private String languages;
	private Date releaseDate;
	private String country;
	
	@Column(nullable = false)
	private String genre;
	private Date movieAddedOn;

	@OneToMany(mappedBy = "movie")
	private List<Show> shows;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMovieDurationInMins() {
		return movieDurationInMins;
	}

	public void setMovieDurationInMins(int movieDurationInMins) {
		this.movieDurationInMins = movieDurationInMins;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getMovieAddedOn() {
		return movieAddedOn;
	}

	public void setMovieAddedOn(Date movieAddedOn) {
		this.movieAddedOn = movieAddedOn;
	}

	@JsonIgnore
	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

}
