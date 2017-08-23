package mx.com.lania.domain.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

	void store(int photographerId, MultipartFile file);

	Stream<Path> loadAll();

	Path load(int photographerId, String fileName);

	Resource loadAsResource(int photographerId, String fileName);

	void deleteAll();

	byte[] getImageByName(int photographerId, String name);
}
