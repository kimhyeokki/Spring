package com.khk11.board.controller;

import com.khk11.board.dao.BoardDao;
import com.khk11.board.dto.BoardDto;
import com.khk11.board.dto.ModalDto;
import com.khk11.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@Slf4j
@RequestMapping("/board")   //post  와  get 둘다 받는다.
@RequiredArgsConstructor    //final을 찾아서 생성자를 만들어준다.
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/list")
    public String list(Model model){
        List<BoardDto> boardList = boardService.getAllBoard();
      /*
        boardList.add(BoardDto.builder()
                .name("김혁기")
                .title("제목01")
                .content("내영2")
                .build());
        boardList.add(BoardDto.builder()
                .name("김혁기02")
                .title("제목003")
                .content("내영03")
                .build());

       */
        model.addAttribute("title","list");
        model.addAttribute("boardList",boardList);
        return "/board/list";
    }
    @GetMapping("/write")
    public String write(Model model){
        BoardDto boardDto = BoardDto.builder().name("장동건").build();

        model.addAttribute("boardDto", boardDto);
        log.info("출력될까여");
        return "/board/write";
    }

    @PostMapping("/write")
    public String writeProcess(@Valid @ModelAttribute BoardDto boardDto,
                               BindingResult bindingResult, Model model,
                               RedirectAttributes redirectAttributes){
       // model.addAttribute("title","write");

        if(bindingResult.hasErrors()){
            model.addAttribute("boardDto", boardDto);
            return "/board/write";
        }
        int result = boardService.insertBoard(boardDto);
        if(result>0){
            ModalDto modalDto = new ModalDto().builder().isState("success").title("글쓰기").
                    msg("글이 입력되었습니다.").build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
        }
        return "redirect:/";
      // redirectAttributes.addAttribute("name","김혁기");
       /* redirectAttributes.addFlashAttribute("name",boardDto.getName());
        redirectAttributes.addAttribute("age",30);*/
        // 새로고침을 한경우 값을 보내 redirect:"/?name=김혁기";
        //RedirectAttributes을 이용해서 값을 보낼 수도 있다.
     /*   if (boardDto.getTitle().isEmpty() && boardDto.getName().isEmpty()&&boardDto.getContent().isEmpty()){
            log.info("값없음");
            return "/board/write";
            //백앤드 단에서 null값이 들어올때 리턴 처리
        }
        log.info(boardDto.getName());
        log.info(boardDto.getTitle());
        log.info(boardDto.getContent());*/

    }
    @GetMapping("/view/{id}")
    @ResponseBody
    public Map<String, Object> getOneBoard(@PathVariable int id) {
        log.info("getOneBoard==={}",id);
        BoardDto boardDto = boardService.getOneBoard(id);
        Map<String, Object> resultMap = new HashMap<>();
        if(boardDto!=null){
            resultMap.put("isState","ok");
            resultMap.put("viewData",boardDto);
        } else {
            resultMap.put("isState", "fail");
            resultMap.put("viewData",null);
        }
        return  resultMap;
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody  //글자를 리턴합니다. 파일을 찾지않고
    public Map<String,String> deleteBoard(@PathVariable int id){
        log.info("ajax으로 넘어온 id==={}",id);
        int reuslt = boardService.deleteBoard(id);
        Map<String,String> resultMap = new HashMap<>();
        if(reuslt>0){
        resultMap.put("isDelete","ok");
        }else {
        resultMap.put("isDelete","fail");
        }
        return resultMap;
    }
}
