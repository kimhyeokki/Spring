package com.khk11.gallery.controller;

import com.khk11.gallery.dto.GalleryDto;
import com.khk11.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {
    private final GalleryService galleryService;

    @GetMapping("/insert")
    public String insertGallery(){
        return "/gallery/insert";
    }
    @PostMapping("/insert")
    public String insertGalleryProcess(@ModelAttribute GalleryDto galleryDto){
        int result =galleryService.insertGallery(galleryDto);
        return "/index";
    }

}
