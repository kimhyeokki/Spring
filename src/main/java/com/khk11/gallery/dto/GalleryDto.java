package com.khk11.gallery.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GalleryDto {
    private int id;
    private String title;
    private String description;
    private String link;
    private String bg;

   // private String file;
    private MultipartFile file;
    //이미지

    private String original;
    private String renamed;
}
