package com.knu.dibly.service;

import com.knu.dibly.domain.diet.UploadFile;
import com.knu.dibly.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UploadFileService {

    private final UploadFileRepository fileRepository;

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        String storedPath = getFullPath(storeFileName);

        multipartFile.transferTo(new File(storedPath));

        UploadFile file = new UploadFile(originalFileName, storeFileName, storedPath);
        fileRepository.save(file);

        return file;
    }

    public String createStoreFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");

        return originalFileName.substring(pos + 1);
    }

}
