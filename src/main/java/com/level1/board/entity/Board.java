package com.level1.board.entity;

import com.level1.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "board") // 매핑할 테이블의 이름 지정
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "password", nullable = false)
    private String password;


    public Board(BoardRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    // 업데이트시 제목과 내용만 업데이트 하기때문에
    public void update(BoardRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
