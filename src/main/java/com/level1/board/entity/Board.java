package com.level1.board.entity;

import com.level1.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;
    private String userName;
    private String contents;
    private String password;

    @CreationTimestamp
    private LocalDate regDate; // 등록날짜

    public static Board from(BoardRequestDto requestDto) {
        return Board.builder()
                .title(requestDto.getTitle())
                .userName(requestDto.getUserName())
                .contents(requestDto.getContents())
                .password(requestDto.getPassword())
                .build();
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.userName = requestDto.getUserName();
        this.contents = requestDto.getContents();
    }
}
