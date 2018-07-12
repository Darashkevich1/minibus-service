package minibus.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import minibus.dao.TokenDAO;
import minibus.entities.Token;

@Service
public final class StorageServiceImpl implements StorageService {
	
	@Autowired
	TokenDAO accessTokenDAO;
	
	private Path rootLocation = Paths.get("images-storage");
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String store(MultipartFile file, String accessToken) {
		Token token = accessTokenDAO.getByToken(accessToken);
		if(token != null) {
	
			String filename = StringUtils.cleanPath(file.getOriginalFilename());
			if(file.isEmpty() || filename.contains("..")) {
				return null;
			}
			try(InputStream in = file.getInputStream()) {
				Files.copy(in, rootLocation.resolve(String.valueOf(token.getUserId())), StandardCopyOption.REPLACE_EXISTING);
				return String.valueOf(token.getUserId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		Path file = load(filename);
		try {
			Resource res = new UrlResource(file.toUri());
			if(res.exists() || res.isReadable()) {
				return res;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void deleteAll() {
		try {
			FileSystemUtils.deleteRecursively(rootLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
