package mx.com.lania.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mx.com.lania.domain.storage.StorageService;
import mx.com.lania.repositories.PhotoRepository;

@Service
public class ImageService {
	private final StorageService storageService;
	private final PhotoRepository photoRepository;

	@Autowired
	public ImageService(StorageService storageService, PhotoRepository photoRepository) {
		this.storageService = storageService;
		this.photoRepository = photoRepository;
	}
	
	public void save(MultipartFile file) {
		storageService.store(file);
		
		// TODO save info to data base
	}
}