package com.level1.board.controller;

import com.level1.board.dto.BoardRequestDto;
import com.level1.board.dto.BoardResponseDto;
import com.level1.board.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api") // 공통된 api 생략하기
public class BoardController {

    // 데이터 전달 방식
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 생성 -> 생성 먼저해야 조회가 가능하니까
    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    // 게시글 전체 조회
    @GetMapping("/boards")
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }

    // 게시글 단일 조회
    @GetMapping("/boards/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    // 수정 요청
    @PutMapping("/boards/{id}") // 아이디는 생성되는 순서로 배정받는 것(실제 id ㄴㄴ), 아이디 받아서 수정하기
    public Long updateBoard(@PathVariable Long id, @Validated @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);
    }

    // 삭제 요청
    @DeleteMapping("/boards/{id}") // 아이디 받아서 삭제하기
    public Long deleteBoard(@PathVariable Long id, @Validated @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteBoard(id, requestDto.getPassword());
    }
}