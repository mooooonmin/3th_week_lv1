package com.level1.board.dto;

import com.level1.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {

    @NotBlank(message = "빈 값일 수 없습니다")
    private String title;

    @NotBlank(message = "빈 값일 수 없습니다")
    private String userName;

    @NotBlank(message = "빈 값일 수 없습니다")
    private String contents;

    @NotBlank(message = "빈 값일 수 없습니다")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,15}$",
            message = "비밀번호는 8~15자리면서 알파벳, 숫자, 특수문자를 포함해야합니다")
    private String password;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .userName(userName)
                .contents(contents)
                .password(password)
                .build();
    }

}
