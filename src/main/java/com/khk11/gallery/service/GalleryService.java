package com.khk11.gallery.service;

import com.khk11.gallery.dao.GalleryDao;
import com.khk11.gallery.dto.GalleryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class GalleryService {
    private final GalleryDao galleryDao;

    @Value("${file.path}")
    private String uploadFolder;



    public int insertGallery(GalleryDto galleryDto) {
        log.info("uploadFolder==={}",uploadFolder);

        UUID uuid = UUID.randomUUID();  //랜던변수를 만든다.
        log.info("originalName===={}",galleryDto.getFile().getOriginalFilename());
        String originalFile = galleryDto.getFile().getOriginalFilename(); //파일이름 오리지널 얻는다.
        String renamedFile = uuid+"_"+originalFile;  //랜덤변수 + 오리지널이름

        Path imgFilePath = Paths.get(uploadFolder+renamedFile);
        // 이미지 파일 경로 추가

        galleryDto.setOriginal(originalFile);  //
        galleryDto.setRenamed(renamedFile);
        try {
            Files.write(imgFilePath,galleryDto.getFile().getBytes());
            // 저장 (파일 경로, 바이트)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int result = galleryDao.insertGallery(galleryDto);
        return result;
    }
    public List<GalleryDto> getAllList(){
        List<GalleryDto> galleryList = galleryDao.getAllList();
        return galleryList;
    }
}
