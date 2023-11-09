package com.khk11.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardDto {

   // @NotNull : 빈값을 허용하지만
   // @NotEmpty : 빈값을 허용하지 않습니다.


    private int id;

    @NotEmpty(message = "이름은 필수입력사항입니다.")
    private String name;

    @NotEmpty(message = "제목은 필수입력사항입니다.")
    @Size(min=5,max=100, message = "최소 5글자 이상 최대 100자까지 가능합니다.")
    private String title;

    @NotEmpty(message = "내용은 필수입력사항입니다.")
    @Size(min=5,max=100, message = "최소 5글자 이상 최대 100자까지 가능합니다.")
    private String content;
    private String regdate;
}
