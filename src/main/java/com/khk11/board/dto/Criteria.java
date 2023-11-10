package com.khk11.board.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Criteria {
    private int currentPage;
    private  int pageSize;  //세로로 리스트 몇개 뿌리는지 확인

    public Criteria(){
        this.currentPage = 1;
        this.pageSize = 10;
    }
    public int getStartPage(){    //StartPage 앞에 get을 붙여서 메소드를 만들면 쿼리에서 #{startPage}로 인식해준다.
        return (currentPage-1)*pageSize+1;
    }
    public int getEndPage(){
        return currentPage*pageSize;
    }

}
