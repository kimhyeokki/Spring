package com.khk11.board.service;

import com.khk11.board.dao.BoardDao;
import com.khk11.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor  //final 을 찾아서 생성자를 만들어준다.
public class BoardService {
    //spring container에 등록된 bean을 주입받을떄 쓴다.
   /* @Autowired
    BoardDao boardDao;*/
    // 되도록이면 Autowired을 쓰지 않는다.
    private final BoardDao boardDao;


    public List<BoardDto> getAllBoard(){
        List<BoardDto> boardList = boardDao.getAllBoard();
        return boardList;
    }
    public  int insertBoard(BoardDto boardDto){
        int result=0;
        result = boardDao.insertBoard(boardDto);
        return  result;
    }
    public BoardDto getOneBoard(int id){
        BoardDto boardDto =null;
        boardDto = boardDao.getOneBoard(id);
        return boardDto;
    }
    public int deleteBoard(int id){
        int result=0;
        result=boardDao.deleteBoard(id);
        return result;
    }
}
