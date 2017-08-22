package mx.com.lania.beans;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.web.multipart.MultipartFile;

public class PhotoBean {
	@FormDataParam("photographer") 
	private int photographer;
	
	
	@FormDataParam("category") 
	private int category;
	
	
	@FormDataParam("file") 
	private MultipartFile file;
	
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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
	
	
}
