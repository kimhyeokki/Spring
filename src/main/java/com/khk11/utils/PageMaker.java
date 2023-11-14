package com.khk11.utils;

import com.khk11.board.dto.Criteria;
import lombok.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@ToString

@Slf4j


public class PageMaker {
    private Criteria criteria; //기준점
    private int total;      //전체 갯수
    private int startPage;  //시작 페이지
    private int endPage;    //마지막 페이지


    private int pageBlock = 5;  // 1/2/3/4/5

    private boolean isPrevPage;
    private boolean isNextPage;
    private int count;


    //전체 페이지 갯수를 정하는 순간 페이지가 만들어짐
    public void setTotal(int total){
        this.total = total;
        makePage();
    }


    public int getLastPage() {
        return (int) Math.ceil( total / (double) criteria.getPageSize());
    }

    private void makePage(){
        //ceil은 올림함수이니깐  1/5는 1임
        endPage = (int)Math.ceil((criteria.getCurrentPage()/(double)pageBlock))*pageBlock;
        startPage = (endPage-pageBlock)+1;


        count = total-(criteria.getCurrentPage() - 1)*criteria.getPageSize();
        // 전체-(현재페이지-1)*10:사이즈 세로 100-(1-1)*10
        //100-(2-1)*10 = 90

        int lastPage = getLastPage();
        log.info("lastPage==={}",lastPage);
        if(endPage > lastPage) endPage = lastPage;
        isPrevPage = startPage==1 ? false:true;
        // 첫페이지가 1이면 false
        isNextPage = lastPage > endPage? true: false;
        //lastPage가 endPage보다 크면 true
        // 90 - (1-1)*10   90 - 1*10


    }

}
