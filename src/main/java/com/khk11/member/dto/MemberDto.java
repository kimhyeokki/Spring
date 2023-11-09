package com.khk11.member.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberDto {
    private String userID;
    private String userPW;

}
