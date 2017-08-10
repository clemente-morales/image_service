package mx.com.lania.resources;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.com.lania.models.storage.StorageFileNotFoundException;
import mx.com.lania.models.storage.StorageService;

@Controller
public class ImageResource {
	private final StorageService storageService;

	@Autowired
	public ImageResource(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/images")
	public ResponseEntity<List<String>> listUploadedImages(Model model) throws IOException {
		List<String> images = 
				storageService.loadAll()
						.map(path ->  path.getFileName()
								.toString())
						.collect(Collectors.toList());

		return ResponseEntity.ok()
				.body(images);
	}

	@GetMapping("/images/{imageName:.+}")
	public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(storageService.getImageByName(imageName), headers, HttpStatus.CREATED);
	}

	@PostMapping("/images")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		storageService.store(file);
		return ResponseEntity.ok()
				.body("You successfully uploaded " + file.getOriginalFilename() + "!");
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}