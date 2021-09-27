package in.co.mbs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 
 * @author santhosh
 *
 */
@Entity
public class Theater {

	@Id
	private String theaterId;
	
	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
	private List<CinemaHall> cinemaHalls;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	public String getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CinemaHall> getCinemaHalls() {
		return cinemaHalls;
	}

	public void setCinemaHalls(List<CinemaHall> cinemaHalls) {
		this.cinemaHalls = cinemaHalls;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
