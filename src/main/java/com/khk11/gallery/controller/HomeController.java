package com.khk11.gallery.controller;

import com.khk11.gallery.dto.GalleryDto;
import com.khk11.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final GalleryService galleryService;
    @GetMapping("/")
    public String home(Model model){
        List<GalleryDto> galleryList = galleryService.getAllList();
        model.addAttribute("galleryList",galleryList);
        return "/index";
    }
    /**/

}
