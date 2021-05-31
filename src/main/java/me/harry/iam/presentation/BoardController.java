package me.harry.iam.presentation;

import java.util.List;
import java.util.stream.Collectors;
import me.harry.iam.application.board.BoardService;
import me.harry.iam.presentation.dto.PostDTO;
import me.harry.iam.presentation.exception.ResponseException;
import me.harry.iam.presentation.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("post")
    public List<PostResponse> readAllPost() {
        return boardService.readPost()
                .stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("post/{id}")
    public PostResponse readPost(@PathVariable Long id) throws ResponseException {
        return new PostResponse(boardService.readPost(id));
    }
}
