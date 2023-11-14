package com.khk11.gallery.dao;

import com.khk11.gallery.dto.GalleryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryDao {
    int insertGallery(GalleryDto galleryDto);

    List<GalleryDto> getAllList();
}
