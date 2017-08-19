package mx.com.lania;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import mx.com.lania.resources.CategoryResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(CategoryResource.class);
	}
	
	
}
