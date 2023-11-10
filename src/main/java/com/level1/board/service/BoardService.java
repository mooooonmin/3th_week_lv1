package com.level1.board.service;

import com.level1.board.dto.BoardRequestDto;
import com.level1.board.dto.BoardResponseDto;
import com.level1.board.entity.Board;
import com.level1.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {

        // 게시글 등록
        Board board = Board.builder()
                .title(requestDto.getTitle())
                .userName(requestDto.getUserName())
                .contents(requestDto.getContents())
                .password(requestDto.getPassword())
                .build();

        boardRepository.save(board);

        return new BoardResponseDto(board, "게시물이 등록되었습니다");
    }

    // 특정 id 게시글 조회
    @Transactional(readOnly = true)
    public BoardResponseDto getBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(NoSuchElementException::new);

        return new BoardResponseDto(board, "조회 완료");
    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> new BoardResponseDto(board, "조회된 게시글"))
                .collect(Collectors.toList());
    }

    // 선택 게시글 수정
    @Transactional
    public void updateBoard(Long boardId, BoardRequestDto requestDto) {
        Board board = getBoardAndCheckPassword(boardId, requestDto.getPassword());
        board.update(requestDto);
    }

    // 선택 게시글 삭제
    @Transactional
    public void deleteBoard(Long boardId, String password) {
        getBoardAndCheckPassword(boardId, password);
        boardRepository.deleteById(boardId);
    }

    // 게시글이 있는지 확인하고, 비밀번호 맞나 확인 -> 수정,삭제시 공통 로직
    private Board getBoardAndCheckPassword(Long boardId, String password) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("게시물이 없습니다"));

        if (!board.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호를 잘못입력하셨습니다");
        }

        return board;
    }

}
