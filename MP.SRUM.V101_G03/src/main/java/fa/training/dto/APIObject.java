package fa.training.dto;

/**
 * @author HoangLV7
 *
 * @param <T>
 */
public class APIObject<T> {
	private T t;
	private String message;

	public APIObject() {
		super();
	}

	public APIObject(T t, String message) {
		super();
		this.t = t;
		this.message = message;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
