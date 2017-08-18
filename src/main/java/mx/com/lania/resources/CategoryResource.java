package mx.com.lania.resources;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return categoryRepository.findAll().iterator();
	}
	
	@PostMapping
	public ResponseEntity<String> handleFileUpload(Category model) {
		Category category = new Category();
		
		categoryRepository.save(category);
		return ResponseEntity.ok()
				.body("You successfully save your category!");
	}
	
	public static <T> Stream<T> stream(Iterable<T> iterable) {
	    return StreamSupport.stream(
	        Spliterators.spliteratorUnknownSize(
	            iterable.iterator(),
	            Spliterator.ORDERED
	        ),
	        false
	    );
	}
}
