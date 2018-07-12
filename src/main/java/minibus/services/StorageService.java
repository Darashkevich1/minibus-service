package minibus.services;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	void init();
	
	String store(MultipartFile file, String accessToken);
	
	Path load(String filename);
	
	Resource loadAsResource(String filename);
	
	void deleteAll();
}
