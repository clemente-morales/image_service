package mx.com.lania.repositories;

import org.springframework.data.repository.CrudRepository;

import mx.com.lania.entities.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {

}
