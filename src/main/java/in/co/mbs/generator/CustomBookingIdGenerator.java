package in.co.mbs.generator;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

import in.co.mbs.utilities.Utility;

/**
 * 
 * @author santhosh
 *
 */
public class CustomBookingIdGenerator implements ValueGenerator<Long> {

	@Override
	public Long generateValue(Session session, Object owner) {
		Long id = null;
		try {
			id = Long.valueOf(Utility.getInstance().getBookingId() + "01");

			Number max = (Number) session.createSQLQuery("select max(id) from booking_checkout")
					.setFlushMode(FlushMode.COMMIT).uniqueResult();
			if (max != null)
				id = Long.valueOf(Utility.getInstance().getBookingId() + "0" + (max.intValue() + 1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
