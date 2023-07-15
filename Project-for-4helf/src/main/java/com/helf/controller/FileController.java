package com.helf.controller;
import com.helf.dto.FileUploadResponse;
import com.helf.entity.Documents;
import com.helf.entity.enums.DocumentType;
import com.helf.service.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
//@Controller
@AllArgsConstructor
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping("/upload")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam DocumentType documentType)throws Exception{
        Documents attachment = null;
        String downloadUrl = "";
        attachment = storageService.saveFile(file,documentType);
        downloadUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getDocumentId())
                .toUriString();
        return new FileUploadResponse(attachment.getDocName(),downloadUrl,file.getContentType(),file.getSize());

    }
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable("fileId") Long fileId) throws Exception {
        Documents fileUpload = null;
        fileUpload = storageService.downloadFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileUpload.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"fileUpload; filename\""+ fileUpload.getDocName()
                        +"\"").body(new ByteArrayResource(fileUpload.getData()));
    }

}
