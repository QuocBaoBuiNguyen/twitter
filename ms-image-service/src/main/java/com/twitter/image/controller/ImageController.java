package com.twitter.image.controller;

import com.twitter.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.gmail.merikbest2015.constants.PathConstants.API_V1_IMAGE;
import static com.gmail.merikbest2015.constants.PathConstants.UPLOAD;

@RestController
@RequestMapping(API_V1_IMAGE)
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    @PostMapping(UPLOAD)
    public String uploadImage(@RequestPart("file") MultipartFile file) {
        return imageService.uploadImage(file);
    }
}
