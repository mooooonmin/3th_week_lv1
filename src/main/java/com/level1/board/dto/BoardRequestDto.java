package com.level1.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BoardRequestDto {

    // 비밀번호는 주로 생성 및 수정 요청과 관련있으므로 requestDto 쪽에 추가하는게 일반적
    // 게시글을 작성하거나 수정할 때 비밀번호를 전달해야하기 때문

    private String title;
    private String username;
    private String contents;
    private String password; // 필수 구현 조건 -> 비밀번호 추가
}
