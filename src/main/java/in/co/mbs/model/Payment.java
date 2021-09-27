package in.co.mbs.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import in.co.mbs.enums.PaymentStatus;

/**
 * 
 * @author santhosh
 *
 */
@Entity
public class Payment {

	@Id
	private String paymentId;
	private double totalAmount;

	@Enumerated
	private PaymentStatus paymentStatus;

	@OneToOne
	@JoinColumn(name = "payment_booking_id", nullable = false)
	private Booking bookingId;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Booking getBookingId() {
		return bookingId;
	}

	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}

}
