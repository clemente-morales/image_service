package mx.com.lania;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("imageService")
public class ImageServiceProperties {
	private String name;
	
	private String description;
	
	private Security security = new Security();
	
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

	public Security getSecurity() {
		return security;
	}

	public static class Security {
		private String user;
		
		private String password;

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

}
