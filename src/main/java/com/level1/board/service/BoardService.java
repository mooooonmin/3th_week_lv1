package com.level1.board.service;

import com.level1.board.dto.BoardRequestDto;
import com.level1.board.dto.BoardResponseDto;
import com.level1.board.entity.Board;
import com.level1.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    // 비즈니스 로직을 처리하고 데이터베이스와 상호작용하는 역할을 함,
    // 그래서 비밀번호 검증 및 게시글 수정, 삭제 로직을 이 클래스에 추가해야 함

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        // RequestDto -> Entity
        Board board = new Board(requestDto);

        // DB 저장
        Board saveBoard = boardRepository.save(board);

        // Entity -> ResponseDto
        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);

        return boardResponseDto;
    }

    public List<BoardResponseDto> getBoards() {
        // DB 조회 -> 존재하는 모든 게시글 조회 -> ModifiedAt 기준으로 내림차순 정렬하기
        // 조회한걸 -> 스트림으로 변환 -> 각 게시글의 엔티티를 BoardResponseDto로 변 -> 그걸다시 리스트로
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto, String password) {

        // BoardRequestDto를 매개변수로 받아오는 이유?
        // 클라이언트가 수정할 게시글의 새로운 내용을 BoardRequestDto 객체로 전달하기 때문(삭제와는 성격이 다름)

        // 해당 게시글이 DB에 존재하는지 확인
        Board board = findBoard(id);

        // 비밀번호 검증 메서드
        validatePassword(board, password);

        // board 내용 수정
        board.update(requestDto);

        return id;
    }

    public Long deleteBoard(Long id, String password) {

        // BoardRequestDto를 매개변수로 받지 않는 이유?
        // 삭제 요청은 새로운 데이터를 생성하거나 수정하는 것이 아니라 삭제하는 것이므로,
        // BoardRequestDto와 같은 수정용 데이터를 받아오는 것은 필요하지 않음

        // 해당 게시글이 DB에 존재하는지 확인
        Board board = findBoard(id);

        // 비밀번호 검증 메서드
        validatePassword(board, password);

        // board 삭제
        boardRepository.delete(board);

        return id;
    }

    private Board findBoard(Long id) {
        // 만약에 조회한 id가 없으면 -> 예외처리
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }

    // 비밀번호 검증 메서드
    private void validatePassword(Board board, String password) {
        if (!board.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
