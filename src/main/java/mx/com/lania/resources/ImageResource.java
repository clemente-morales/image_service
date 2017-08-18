package mx.com.lania.resources;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.com.lania.domain.storage.StorageFileNotFoundException;
import mx.com.lania.domain.storage.StorageService;
import mx.com.lania.services.ImageService;

@RestController
@RequestMapping("/images")
public class ImageResource {
	private final StorageService storageService;
	private final ImageService imageService;

	@Autowired
	public ImageResource(StorageService storageService, ImageService imageService) {
		this.storageService = storageService;
		this.imageService = imageService;
	}

	@GetMapping
	public ResponseEntity<List<String>> listUploadedImages(Model model) throws IOException {
		List<String> images = 
				storageService.loadAll()
						.map(path ->  path.getFileName()
								.toString())
						.collect(Collectors.toList());

		return ResponseEntity.ok()
				.body(images);
	}

	@GetMapping("/{imageName:.+}")
	public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(storageService.getImageByName(imageName), headers, HttpStatus.CREATED);
	}

	@PostMapping
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		imageService.save(file);
		return ResponseEntity.ok()
				.body("You successfully uploaded " + file.getOriginalFilename() + "!");
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}