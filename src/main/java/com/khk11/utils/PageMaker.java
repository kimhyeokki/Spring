package com.khk11.utils;

import com.khk11.board.dto.Criteria;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@ToString
public class PageMaker {
    private Criteria criteria; //기준점
    private int total;      //전체 갯수
    private int startPage;  //시작 페이지
    private int endPage;    //마지막 페이지
    private int pageBlock=5;  // 1/2/3/4/5

    private boolean isPrevPage;
    private boolean isNextPage;
    //전체 페이지 갯수를 정하는 순간 페이지가 만들어짐
    public void setTotal(int total){
        this.total = total;
        makePage();
    }
    private void makePage(){
        //ceil은 올림함수이니깐  1/5는 1임
        endPage = (int)Math.ceil((criteria.getCurrentPage()/(double)pageBlock))*pageBlock;
        startPage = (endPage-pageBlock)+1;
    }

}
