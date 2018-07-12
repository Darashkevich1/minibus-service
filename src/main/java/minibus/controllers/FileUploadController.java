package minibus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import minibus.services.StorageService;

@RestController
@RequestMapping("/file")
@CrossOrigin("*")
public class FileUploadController {
	
	@Autowired
	StorageService storageServiceImpl;
	
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, @RequestParam("accessToken") String accessToken) {
		return storageServiceImpl.store(file, accessToken);
	}
	
	@GetMapping("/load/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> load(@PathVariable("filename") String filename) {
		Resource res = storageServiceImpl.loadAsResource(filename);
		if(res != null) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_ENCODING, "attachment; filename=\""+res.getFilename()+"\"").body(res);
		}
		return null;
	}
}
