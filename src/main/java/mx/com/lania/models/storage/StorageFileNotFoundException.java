package mx.com.lania.models.storage;

public class StorageFileNotFoundException extends StorageException {
	private static final long serialVersionUID = 2380142521891347803L;

	public StorageFileNotFoundException(String message) {
		super (message);
	}
	
	public StorageFileNotFoundException(String message, Throwable cause) {
		super (message, cause);
	}
	
}
