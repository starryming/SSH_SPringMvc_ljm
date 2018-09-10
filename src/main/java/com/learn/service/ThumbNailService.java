package com.learn.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

public interface ThumbNailService {
    String generateThumbnail(CommonsMultipartFile file, String realUploadPath) throws IOException;
}
