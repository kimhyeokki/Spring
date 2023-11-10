package com.khk11.board.dao;

import com.khk11.board.dto.BoardDto;
import com.khk11.board.dto.Criteria;
import org.apache.ibatis.annotations.Mapper;


import java.util.HashMap;
import java.util.List;

//dao가 db에 dto 입출력
@Mapper
public interface BoardDao{
 /*   public List<BoardDto> getAllBoard(HashMap<String,Object> map);*/

    public List<BoardDto> getAllBoard(Criteria criteria);

    int insertBoard(BoardDto boardDto);

    public BoardDto getOneBoard(int id);

    int deleteBoard(int id);

    int modifyBoard(BoardDto boardDto);

    int getTotalCount();
}
