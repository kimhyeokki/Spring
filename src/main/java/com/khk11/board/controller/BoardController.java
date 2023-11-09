package com.khk11.board.controller;

import com.khk11.board.dao.BoardDao;
import com.khk11.board.dto.BoardDto;
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

import java.util.ArrayList;
import java.util.List;

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
            redirectAttributes.addFlashAttribute("isState","success");
        }
       /* redirectAttributes.addAttribute("name","김혁기");
        redirectAttributes.addFlashAttribute("name",boardDto.getName());
        redirectAttributes.addAttribute("age",30);*/
        return "redirect:/";
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
    public String getOneBoard(@PathVariable int id){
        log.info("dasd",id);
        boardService.getOneBoard(id);
        return "/board/view";
    }
}
