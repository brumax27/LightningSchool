package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.delegate.aws.MediaStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
public class MediaTestController {

    @Autowired
    private MediaStoreService mediaStoreService;

    @RequestMapping(value = "", headers = "Content-Type= multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
       String urlFile = mediaStoreService.putMedia(file.getOriginalFilename(), file);
       return ResponseEntity.ok(urlFile);
    }
}
