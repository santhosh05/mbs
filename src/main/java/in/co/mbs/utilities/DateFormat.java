package in.co.mbs.utilities;

/**
 * 
 * @author santhosh
 *
 */
public enum DateFormat {
	DD_MM_YYYY("dd_MM_yyyy"), DB_FORMAT("yyyy-MM-dd HH:mm:ss"), BOOKING_ID_FORMAT("ddMMyyyyhhmmss");

	private String format;

	DateFormat(String format) {
		this.format = format;
	}

	@Override
	public String toString() {
		return format;
	}

}
