package com.level1.board.controller;

import com.level1.board.dto.BoardRequestDto;
import com.level1.board.dto.BoardResponseDto;
import com.level1.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    // 게시글 생성
    @PostMapping()
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    // 게시글 전체 조회
    @GetMapping()
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getAllBoards();
    }

    // 게시글 단일 조회
    @GetMapping("/{boardId}")
    public BoardResponseDto getBoardById(@PathVariable Long boardId) {
        return boardService.getBoardById(boardId);
    }

    // 선택 게시글 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable Long boardId,
                                         @Validated @RequestBody BoardRequestDto requestDto) {
        boardService.updateBoard(boardId, requestDto);
        BoardResponseDto updatedBoardResponse = boardService.getBoardById(boardId);
        return ResponseEntity.ok(updatedBoardResponse);
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardId,
                                         @RequestBody BoardRequestDto requestDto) {
        boardService.deleteBoard(boardId, requestDto.getPassword());
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }
}