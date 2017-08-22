package mx.com.lania.beans;

import javax.ws.rs.FormParam;

public class PhotographerBean {
	@FormParam("name") 
	private String name;
	
	@FormParam("email") 
	private String email;
	
	@FormParam("phone") 
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
