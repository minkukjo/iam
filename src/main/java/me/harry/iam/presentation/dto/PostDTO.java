package me.harry.iam.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import me.harry.iam.domain.board.Type;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class PostDTO {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Type type;
}
