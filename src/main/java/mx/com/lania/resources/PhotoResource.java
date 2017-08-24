package mx.com.lania.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mx.com.lania.beans.PhotoBean;
import mx.com.lania.entities.Photo;
import mx.com.lania.repositories.PhotoRepository;
import mx.com.lania.services.ImageService;
import mx.com.lania.tos.Picture;

@Component
@Path("/photos")
@Produces("application/json")
public class PhotoResource {
	private final PhotoRepository photoRepository;
	private final ImageService imageService;

	@Context
	private UriInfo uriInfo;

	@Autowired
	public PhotoResource(ImageService imageService, PhotoRepository photoRepository) {
		this.imageService = imageService;
		this.photoRepository = photoRepository;
	}

	@GET
	public List<Photo> getAll() {
		Stream<Photo> stream = StreamSupport.stream(photoRepository.findAll().spliterator(), false);
		return stream.collect(Collectors.toList());
	}
	
	@GET
	@Path("like/{name}")
	@Transactional
	public List<Photo> getAllByNameLike(@PathParam("name") String name) {
		String value = String.format("%%%s%%", name);
		Stream<Photo> stream = StreamSupport.stream(photoRepository.findByNameLike(value).spliterator(), false);
		return stream.collect(Collectors.toList());
	}
	
	@GET
	@Path("photographer/{photographerId}")
	public List<Photo> findByPhotographerId(@PathParam("photographerId") int photographerId) {
		return imageService.findByPhotographerId(photographerId);
	}

	@GET
	@Path("{id}")
	public Photo getPhotoById(@PathParam("id") int id) {
		return photoRepository.findOne(id);
	}
	
	@GET
	@Path("{id}")
	@Produces("image/*")
	public Response getImageById(@PathParam("id") int id) {
		Photo photo = photoRepository.findOne(id);
		Picture image = imageService.getPhoto(photo.getPhotographer().getId(), photo.getImagePath());
		return Response.ok(image.getImage(), image.getMimeType()).build();
	}

	@PUT
	public Response save(Photo photo) {
		photoRepository.save(photo);
		return publishSavingResult(photo.getId());
	}

	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@POST
	public Response saveForm(@BeanParam PhotoBean photoBean) {
		int id = imageService.save(photoBean);
		return publishSavingResult(id);
	}

	private Response publishSavingResult(int id) {
		URI location = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", id).build();
		return Response.created(location).build();
	}

}
