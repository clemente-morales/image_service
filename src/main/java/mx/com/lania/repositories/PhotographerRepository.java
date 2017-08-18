package mx.com.lania.repositories;

import org.springframework.data.repository.CrudRepository;

import mx.com.lania.entities.Photographer;

public interface PhotographerRepository extends CrudRepository<Photographer, Integer> {

}
