package com.level1.board.dto;

import com.level1.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    // 여기서 id는 데이터베이스에 저장되는 순번임 -> 이 프로젝트에는 로그인구현을 하지 않았기 때문에
    // 데이터베이스에서 순차적으로 생성되는 값으로 pk를 지정하는 듯? not null & unique 값이 그것밖에 없어서
    private Long id;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.username = board.getUsername();
        this.contents = board.getContents();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}
