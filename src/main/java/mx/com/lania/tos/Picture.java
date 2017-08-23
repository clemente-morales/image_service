package mx.com.lania.tos;

public class Picture {
	private final String mimeType;
	private final byte[] image;

	public Picture(String mimeType, byte[] image) {
		this.mimeType = mimeType;
		this.image = image;
	}

	public String getMimeType() {
		return mimeType;
	}

	public byte[] getImage() {
		return image;
	}
}
