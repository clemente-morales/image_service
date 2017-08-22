package mx.com.lania.resources;

import java.net.URI;
import java.util.List;
import java.util.OptionalInt;
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

import mx.com.lania.beans.CategoryBean;
import mx.com.lania.entities.Category;
import mx.com.lania.repositories.CategoryRepository;

@Component
@Path("/categories")
@Produces("application/json")
public class CategoryResource {
	private final CategoryRepository categoryRepository;

	@Context
	private UriInfo uriInfo;

	@Autowired
	public CategoryResource(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@GET
	public List<Category> getAll() {

		Stream<Category> stream = StreamSupport.stream(categoryRepository.findAll().spliterator(), false);

		return stream.collect(Collectors.toList());
	}

	@GET
	@Path("{id}")
	public Category getById(@PathParam("id") int id) {
		return categoryRepository.findOne(id);
	}
	
	
	@Consumes("application/x-www-form-urlencoded")
	@PUT
	public Response saveForm(@BeanParam CategoryBean categoryBean) {
		
		Stream<Category> stream = StreamSupport.stream(categoryRepository.findAll().spliterator(), false);
		OptionalInt optionalId = stream.mapToInt(category-> category.getId()).max();
		
		int id = optionalId.orElse(0);
	
		Category category = new Category();
		category.setActive(true);
		category.setDescription(categoryBean.getDescription());
		category.setName(categoryBean.getName());
		category.setId(++id);
		return save(category);
	}
	

	@PUT
	public Response save(Category category) {
		categoryRepository.save(category);
		URI location = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", category.getId()).build();

		return Response.created(location).build();
	}
}
