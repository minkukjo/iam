package me.harry.iam.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@NoArgsConstructor
public class PostDTO {

    @NotBlank
    private String title;

    private String content;
}
