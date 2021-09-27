package in.co.mbs.exceptions;

/**
 * 
 * @author santhosh
 *
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomException(String message) {
		super(message);
	}

}
