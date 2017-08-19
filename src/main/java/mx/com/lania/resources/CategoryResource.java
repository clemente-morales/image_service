package mx.com.lania.resources;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import mx.com.lania.entities.Category;
import mx.com.lania.repositories.CategoryRepository;

@Component
@Path("/categories")
@Produces("application/json")
public class CategoryResource {
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryResource(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@GET
	public List<Category> getAll() {
		
		Stream<Category> stream = StreamSupport.stream(categoryRepository.findAll().spliterator(), false);
		
		return stream.collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity<String> handleFileUpload(Category model) {
		Category category = new Category();
		
		categoryRepository.save(category);
		return ResponseEntity.ok()
				.body("You successfully save your category!");
	}
}
