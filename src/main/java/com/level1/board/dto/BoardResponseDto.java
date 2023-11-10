package com.level1.board.dto;

import com.level1.board.entity.Board;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardResponseDto {

    private String title;
    private String userName;
    private String contents;
    private String message; // 게시물 등록시 메세지 반환 추가

    public BoardResponseDto(Board board, String message) {

        this.title = board.getTitle();
        this.userName = board.getUserName();
        this.contents = board.getContents();
        this.message = message;
    }
}
