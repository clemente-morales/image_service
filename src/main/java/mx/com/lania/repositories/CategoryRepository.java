package mx.com.lania.repositories;

import org.springframework.data.repository.CrudRepository;

import mx.com.lania.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
