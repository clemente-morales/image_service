package mx.com.lania.beans;

import javax.ws.rs.FormParam;

public class CategoryBean {
	@FormParam("name") 
	private String name;
	
	@FormParam("description") 
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
