package mx.com.lania.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

import mx.com.lania.entities.Photographer;
import mx.com.lania.repositories.PhotographerRepository;

@Component
@Path("/photographers")
@Produces("application/json")
public class PhotographerResource {
	private PhotographerRepository photographerRepository;

	@Context
	private UriInfo uriInfo;

	@Autowired
	public PhotographerResource(PhotographerRepository photographerRepository) {
		this.photographerRepository = photographerRepository;
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

	@PUT
	public Response save(Photographer category) {
		photographerRepository.save(category);
		URI location = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", category.getId()).build();

		return Response.created(location).build();
	}
}
