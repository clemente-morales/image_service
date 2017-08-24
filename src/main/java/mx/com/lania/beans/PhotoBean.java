package mx.com.lania.beans;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

public class PhotoBean {
	@FormDataParam("photographer") 
	private int photographer;
	
	
	@FormDataParam("category") 
	private int category;
	
	
	@FormDataParam("file") 
	private InputStream file;
	
	@FormDataParam("file") 
	private FormDataContentDisposition contentdisposition;
	
	@FormDataParam("name") 
	private String name;
	
	@FormDataParam("description") 
	private String description;


	public int getPhotographer() {
		return photographer;
	}


	public void setPhotographer(int photographer) {
		this.photographer = photographer;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public InputStream getFile() {
		return file;
	}


	public void setFile(InputStream file) {
		this.file = file;
	}


	public FormDataContentDisposition getContentdisposition() {
		return contentdisposition;
	}


	public void setContentdisposition(FormDataContentDisposition contentdisposition) {
		this.contentdisposition = contentdisposition;
	}
}
