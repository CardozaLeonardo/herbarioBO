package util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicioArchivo {
	
	//@Value("${app.upload.dir:${user.home}}")
	public String uploadDir = "/home/leonardo/Documents/workspace-sts-3.9.10.RELEASE" +
	"/herbario/src/main/webapp/storage";
	
	public boolean subirArchivo(MultipartFile file) {
		boolean guardado = true;
		
		try {
			Path copyLocation = Paths.get(uploadDir + File.separator + 
					StringUtils.cleanPath(file.getOriginalFilename()));
			
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e) {
			guardado = false;
			e.getMessage();
			e.printStackTrace();
		}
		
		return guardado;
	}
}
