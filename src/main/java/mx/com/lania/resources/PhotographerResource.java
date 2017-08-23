package mx.com.lania.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.lania.beans.PhotographerBean;
import mx.com.lania.entities.Photo;
import mx.com.lania.entities.Photographer;
import mx.com.lania.repositories.PhotographerRepository;
import mx.com.lania.services.ImageService;
import mx.com.lania.tos.Picture;

@Component
@Path("/photographers")
@Produces("application/json")
public class PhotographerResource {
	private PhotographerRepository photographerRepository;
	private ImageService imageService;

	@Context
	private UriInfo uriInfo;

	@Autowired
	public PhotographerResource(PhotographerRepository photographerRepository, ImageService imageService) {
		this.photographerRepository = photographerRepository;
		this.imageService = imageService;
	}

	@GET
	public List<Photographer> getAll() {
		Stream<Photographer> stream = StreamSupport.stream(photographerRepository.findAll().spliterator(), false);
		return stream.collect(Collectors.toList());
	}

	@GET
	@Path("{id}")
	public Photographer getById(@PathParam("id") int id) {
		return photographerRepository.findOne(id);
	}
	
	@GET
	@Path("{id}/photos")
	public List<Photo> getAllImagesByPhotographer(@PathParam("id") int id) {
		return imageService.findByPhotographerId(id);
	}
	
	@GET
	@Path("{photographerId}/photos/{imageName}")
	@Produces("image/*")
	public Response getPhoto(@PathParam("photographerId") int photographerId, @PathParam("imageName") String imageName) {
		Picture image = imageService.getPhoto(photographerId, imageName);
		return Response.ok(image.getImage(), image.getMimeType()).build();
	}

	@PUT
	public Response save(Photographer category) {
		photographerRepository.save(category);
		URI location = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", category.getId()).build();

		return Response.created(location).build();
	}

	@Consumes("application/x-www-form-urlencoded")
	@PUT
	public Response saveForm(@BeanParam PhotographerBean photographerBean) {
		Photographer photographer = new Photographer();
		photographer.setActive(true);
		photographer.setName(photographerBean.getName());
		photographer.setEmail(photographerBean.getEmail());
		photographer.setPhone(photographerBean.getPhone());

		return save(photographer);
	}
}
