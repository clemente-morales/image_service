package mx.com.lania;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import mx.com.lania.resources.CategoryResource;
import mx.com.lania.resources.PhotoResource;
import mx.com.lania.resources.PhotographerResource;

@Configuration
@ApplicationPath("/photo_gallery")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(CategoryResource.class);
		register(PhotographerResource.class);
		register(PhotoResource.class);
	}
}
