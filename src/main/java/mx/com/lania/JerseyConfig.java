package mx.com.lania;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import mx.com.lania.resources.CategoryResource;
import mx.com.lania.resources.PhotographerResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(CategoryResource.class);
		register(PhotographerResource.class);
	}
}
