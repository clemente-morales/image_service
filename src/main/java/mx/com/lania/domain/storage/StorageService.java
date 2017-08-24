package mx.com.lania.domain.storage;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;

public interface StorageService {

	void init();

	void store(int photographerId, InputStream file, String fileName);

	Stream<Path> loadAll();

	Path load(int photographerId, String fileName);

	Resource loadAsResource(int photographerId, String fileName);

	void deleteAll();

	byte[] getImageByName(int photographerId, String name);
}
