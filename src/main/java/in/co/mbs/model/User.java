package in.co.mbs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import in.co.mbs.enums.Role;

/**
 * 
 * @author santhosh
 *
 */
@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String username;
	@Column(nullable = false, unique = true)
	private String mailId;
	@Column(nullable = false)
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@Column(unique = true)
	private String mobileNo;

	@OneToMany(mappedBy = "user")
	private List<Booking> bookings;

	@Enumerated
	private Role role;

	public User() {

	}

	public User(User user) {
		super();
		this.username = user.getUsername();
		this.mailId = user.getMailId();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.mobileNo = user.getMobileNo();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
