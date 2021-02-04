package org.zerock.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.service.BoardService;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board/")
public class BoardController {

    private final BoardService service;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list...." + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto... " + dto);
        Long bno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO requestDTO, Long bno,Model model) {
        log.info("bno... " + bno);

        BoardDTO boardDTO = service.get(bno);

        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes) {
        log.info("bno... " + bno);
        service.removeWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {
        log.info("post modify ... " + dto + "\n" + pageRequestDTO);
        service.modify(dto);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        return "redirect:/board/list";
    }
}
