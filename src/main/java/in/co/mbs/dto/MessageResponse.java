package in.co.mbs.dto;

/**
 * 
 * @author santhosh
 *
 */
public class MessageResponse {

	private String message;
	private Object dataObject;

	public MessageResponse(String message) {
		this.message = message;
	}

	public MessageResponse(String message, Object dataObject) {
		this.message = message;
		this.dataObject = dataObject;
	}

	public String getMessage() {
		return message;
	}

	public Object getDataObject() {
		return dataObject;
	}
}
