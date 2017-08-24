package mx.com.lania.domain.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

@Service
public class FileSystemStorageService implements StorageService {
	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}

	}

	@Override
	public void store(int photographerId, InputStream file, String fileName) {
		try {
			if (file == null)
				throw new StorageException("Failed to store empty file" + fileName);

			if (fileName.contains(".."))
				throw new StorageException(
						"Cannot store file with relative path outside current directory " + fileName);
			
			Path photographerDirectory = this.rootLocation.resolve(Integer.toString(photographerId));
			if (Files.notExists(photographerDirectory))
				Files.createDirectory(photographerDirectory);
			
			Path filePath = photographerDirectory.resolve(fileName);

			Files.copy(file, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new StorageException("Failed to store file" + fileName, e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}
	}

	@Override
	public Path load(int photographerId, String fileName) {
		return rootLocation.resolve(Integer.toString(photographerId)).resolve(fileName);
	}

	@Override
	public Resource loadAsResource(int photographerId, String fileName) {
		try {
			Path file = load(photographerId, fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable())
				return resource;
			else
				throw new StorageFileNotFoundException("Could not read file" + fileName);

		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file" + fileName, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public byte[] getImageByName(int photographerId, String fileName) {
		try {
			Path file = load(photographerId, fileName);
			return Files.readAllBytes(file);
		} catch (IOException e) {
			throw new StorageFileNotFoundException("Could not read file" + fileName, e);
		}
	}

}
