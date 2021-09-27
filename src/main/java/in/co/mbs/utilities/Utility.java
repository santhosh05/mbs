package in.co.mbs.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import in.co.mbs.enums.PaymentStatus;

/**
 * 
 * @author santhosh
 *
 */
public class Utility {
	private static Utility utility = null;

	private Utility() {
	}

	public static Utility getInstance() {
		if (utility == null)
			synchronized (Utility.class) {
				if (utility == null)
					utility = new Utility();
			}
		return utility;
	}

	public Date addMinutesToDate(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

	public Date reduceMinutesToDate(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -minutes);
		return calendar.getTime();
	}

	public Date reduceSecondsToDate(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, -seconds);
		return calendar.getTime();
	}

	public String formatDate(Date date, DateFormat format) {
		return new SimpleDateFormat(format.toString()).format(date);
	}

	public Long getBookingId() {
		return Long.valueOf(formatDate(new Date(), DateFormat.BOOKING_ID_FORMAT));
	}

	public String getPaymentId(Long bookingId) {
		return "P-" + bookingId;
	}

	public PaymentStatus getPaymentStatus() {
		int max = 10;
		int min = 1;
		int randomNumber = new Random().nextInt(max + 1 - min) + min;
		if (randomNumber > 8)
			return PaymentStatus.FAILURE;
		return PaymentStatus.SUCCESS;
	}
}
