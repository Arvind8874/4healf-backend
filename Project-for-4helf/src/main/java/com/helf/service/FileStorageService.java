package com.helf.service;
import com.helf.entity.Documents;
import com.helf.entity.enums.DocumentType;
import com.helf.repository.FileDBRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public Documents saveFile(MultipartFile file, DocumentType documentType) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("The file name is invalid" + fileName);

            }
           // Documents fileUpload = new Documents(fileName, file.getContentType(), file.getBytes());
            Documents documents = new Documents();
            documents.setDocType(documentType);
            documents.setDocName(file.getOriginalFilename());
            documents.setData(file.getBytes());
            documents.setFileType(file.getContentType());
            documents.setDocumentId(UUID.randomUUID().toString());
            documents.setCreatedTime(new Date().getTime());
            documents.setModifyTime(new Date().getTime());
            return fileDBRepository.save(documents);
        } catch (Exception e) {
            throw new Exception("File could not be save");
        }
    }

    public Documents downloadFile(Long fileId) throws Exception {
        return fileDBRepository.findById(fileId)
                .orElseThrow(() -> new Exception("A file with Id :" + fileId + "could not be found"));
    }
}






