package io.kidan.inlet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class FilesStorageService {
    @Value("${upload.path}")
    private String path;

    FilesStorageService() {}

    public void saveFile (MultipartFile inputFile) throws IOException {

        inputFile.transferTo(new File(path + inputFile.getOriginalFilename()));
    }

    public HashMap<String, String> getFileDetails (MultipartFile inputFile) {
        HashMap<String, String> fileDetails = new HashMap<>();
        String fileName = inputFile.getOriginalFilename();
        String filePath = path + fileName;

        fileDetails.put("fileName", fileName);
        fileDetails.put("filePath", filePath);

        return fileDetails;
    }

}
