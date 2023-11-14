package com.khk11.board.controller;

import com.khk11.board.dao.BoardDao;
import com.khk11.board.dto.BoardDto;
import com.khk11.board.dto.Criteria;
import com.khk11.board.dto.ModalDto;
import com.khk11.board.service.BoardService;
import com.khk11.utils.PageMaker;
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
    private final PageMaker pageMaker;

  /*  @GetMapping("/list")
    public String list(Model model,@RequestParam(required = false) String category, @RequestParam(required = false) String searchTxt){
       log.info("category==={}",category);
        List<BoardDto> boardList = boardService.getAllBoard(category,searchTxt);
        model.addAttribute("title","list");
        model.addAttribute("boardList",boardList);
        return "/board/list";

    }*/
    @GetMapping("/list")
    public String list(Model model, Criteria criteria){

        List<BoardDto> boardList = boardService.getAllBoard(criteria);
        pageMaker.setCriteria(criteria);
        pageMaker.setTotal(boardService.getTotalCount());
        model.addAttribute("boardList",boardList);
        model.addAttribute("pageMaker",pageMaker);
        log.info("pageMaker===={}",pageMaker.toString());
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
  //  @ResponseBody
    public String getOneBoard(@PathVariable int id, Model model) {
        log.info("getOneBoard==={}",id);
        BoardDto boardDto = boardService.getOneBoard(id);
        model.addAttribute("boardDto",boardDto);
       /* Map<String, Object> resultMap = new HashMap<>();
        if(boardDto!=null){
            resultMap.put("isState","ok");
            resultMap.put("viewData",boardDto);
        } else {
            resultMap.put("isState", "fail");
            resultMap.put("viewData",null);
        }*/
        return  "/board/view";
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
    @GetMapping("/delete/{id}")
        public String deleteBoard(@PathVariable int id,
                                  @RequestParam(required = false) int currentPage,
                                    RedirectAttributes redirectAttributes){
            log.info("currentPage==={}",currentPage);
            int result = boardService.deleteBoard(id);
            if(result>0){
                ModalDto modalDto = ModalDto.builder()
                        .isState("success")
                        .title("글삭제")
                        .msg(id+"번째 글이 삭제되었습니다.").build();
                redirectAttributes.addFlashAttribute("modalDto",modalDto);
                return "redirect:/board/list?currentPage="+currentPage;
            }
            return "redirect:/board/list?currentPage="+currentPage;
        }
    @GetMapping("/modify/{id}")
    //  @ResponseBody
    public String modifyBoard(@PathVariable int id, Model model) {
        log.info("getOneBoard==={}", id);
        BoardDto boardDto = boardService.getOneBoard(id);
        model.addAttribute("boardDto", boardDto);
        return "/board/modify";
    }
    //유효성 검사
    @PostMapping("/modify")
    //  @ResponseBody
    public String modifyProcessBoard(@Valid @ModelAttribute BoardDto boardDto,
                                     BindingResult bindingResult, Model model,
                                     RedirectAttributes redirectAttributes,
                                     @RequestParam(required = false) int currentPage) {
        if(bindingResult.hasErrors()){
            model.addAttribute("boardDto",boardDto);
            return "/board/modify";
        }
       int result = boardService.modifyBoard(boardDto);
        if(result>0){
            ModalDto modalDto = new ModalDto().builder().isState("success").title("글수정").
                    msg("글이 수정되었습니다..").build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
            return "redirect:/board/list?currentPage="+currentPage;
        }
        return "redirect:/board/list?currentPage="+currentPage;
    }
    @PostMapping("/test")

    public String test(@RequestParam int id, @RequestParam(required = false) int currentPage,
                       RedirectAttributes redirectAttributes){
        int result = boardService.deleteBoard(id);
        if(result>0){
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .title("글삭제")
                    .msg(id+"번째 글이 삭제되었습니다.").build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
            return "redirect:/board/list?currentPage="+currentPage;
        }
        return "redirect:/board/list?currentPage="+currentPage;
    }
}
