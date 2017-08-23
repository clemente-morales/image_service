package mx.com.lania.services;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.lania.beans.PhotoBean;
import mx.com.lania.domain.storage.StorageService;
import mx.com.lania.entities.Photo;
import mx.com.lania.repositories.CategoryRepository;
import mx.com.lania.repositories.PhotoRepository;
import mx.com.lania.repositories.PhotographerRepository;
import mx.com.lania.tos.Picture;

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
		storageService.store(photoBean.getPhotographer(), photoBean.getFile());

		Photo photo = new Photo();
		photo.setActive(true);
		photo.setCategory(categoryRepository.findOne(photoBean.getCategory()));
		photo.setPhotographer(photographerRepository.findOne(photoBean.getPhotographer()));
		photo.setDescription(photoBean.getDescription());
		photo.setName(photoBean.getName());
		photo.setImagePath(photoBean.getFile().getOriginalFilename());
		photoRepository.save(photo);
		
		return photo.getId();
	}

	public List<Photo> findByPhotographerId(int photographerId) {
		List<Photo> result = new ArrayList<>();
		
		try (Stream<Photo> photos = photoRepository.findByPhotographerId(photographerId)) {
			result = photos.collect(Collectors.toList());
		}

		return result;
	}

	public Picture getPhoto(int photographerId, String imageName) {
		
		Path imagePath = storageService.load(photographerId, imageName);
		String mimeType = new MimetypesFileTypeMap().getContentType(imagePath.toFile());
		byte[] image = storageService.getImageByName(photographerId, imageName);
		
		return new Picture(mimeType, image);
	}
}