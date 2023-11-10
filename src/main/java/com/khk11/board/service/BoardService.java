package com.khk11.board.service;

import com.khk11.board.dao.BoardDao;
import com.khk11.board.dto.BoardDto;
import com.khk11.board.dto.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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


   /* public List<BoardDto> getAllBoard(String category, String searchTxt){
        HashMap<String, Object> map = new HashMap<>();
        map.put("category",category);
        map.put("searchTxt",searchTxt);
        List<BoardDto> boardList = boardDao.getAllBoard(map);
        return boardList;
    }*/

    public List<BoardDto> getAllBoard(Criteria criteria){
        /*HashMap<String, Object> map = new HashMap<>();
        map.put("category",category);
        map.put("searchTxt",searchTxt);*/
        List<BoardDto> boardList = boardDao.getAllBoard(criteria);
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

    public int modifyBoard(BoardDto boardDto) {
         int result =   boardDao.modifyBoard(boardDto);
        return result;
    }
    public int getTotalCount(){
        int count = boardDao.getTotalCount();
        return count;
    }
}
