package mx.com.lania.models.storage;

public class StorageException extends RuntimeException {
	private static final long serialVersionUID = 2437046618716233245L;

	public StorageException(String message) {
		super (message);
	}
	
	public StorageException(String message, Throwable cause) {
		super (message, cause);
	}
	
}
