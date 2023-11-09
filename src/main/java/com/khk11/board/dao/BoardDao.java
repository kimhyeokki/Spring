package com.khk11.board.dao;

import com.khk11.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

//dao가 db에 dto 입출력
@Mapper
public interface BoardDao{
    public List<BoardDto> getAllBoard();

    int insertBoard(BoardDto boardDto);

    public BoardDto getOneBoard(int id);

    int deleteBoard(int id);
}
