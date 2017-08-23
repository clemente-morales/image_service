package mx.com.lania.repositories;

import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import mx.com.lania.entities.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
	Stream<Photo> findByPhotographerId(int id);
	Stream<Photo> findByNameLike(String name);
}
