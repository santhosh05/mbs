package in.co.mbs.exceptions;

/**
 * 
 * @author santhosh
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resourceType, String resourceIdType, String resourceId) {
		super("Resource Not Found Exception -> [" + resourceType + "] - " + resourceIdType + " - " + resourceId);
	}

}
