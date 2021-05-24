package me.harry.iam.presentation;

import me.harry.iam.application.board.BoardService;
import me.harry.iam.presentation.dto.PostDTO;
import me.harry.iam.presentation.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("post")
    public PostResponse writePost(@RequestBody @Valid PostDTO postDTO) {
        return new PostResponse(boardService.writePost(postDTO));
    }
}
