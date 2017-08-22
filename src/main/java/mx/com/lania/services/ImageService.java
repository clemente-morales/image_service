package mx.com.lania.services;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lania.beans.PhotoBean;
import mx.com.lania.domain.storage.StorageService;
import mx.com.lania.entities.Photo;
import mx.com.lania.repositories.CategoryRepository;
import mx.com.lania.repositories.PhotoRepository;
import mx.com.lania.repositories.PhotographerRepository;

@Service
public class ImageService {
	private final StorageService storageService;
	private final PhotoRepository photoRepository;
	private final CategoryRepository categoryRepository;
	private final PhotographerRepository photographerRepository;

	@Autowired
	public ImageService(StorageService storageService, PhotoRepository photoRepository,
			CategoryRepository categoryRepository, PhotographerRepository photographerRepository) {
		this.storageService = storageService;
		this.photoRepository = photoRepository;
		this.categoryRepository = categoryRepository;
		this.photographerRepository = photographerRepository;
	}

	public int save(PhotoBean photoBean) {
		storageService.store(photoBean.getFile());

		Photo photo = new Photo();
		photo.setActive(true);
		photo.setCategory(categoryRepository.findOne(photoBean.getCategory()));
		photo.setPhotographer(photographerRepository.findOne(photoBean.getPhotographer()));
		photo.setDescription(photoBean.getDescription());
		photo.setName(photoBean.getName());
		photo.setImagePath(photoBean.getFile().getOriginalFilename());

		return 0;
	}
}