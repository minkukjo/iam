package me.harry.iam.presentation;

import me.harry.iam.application.board.BoardService;
import me.harry.iam.domain.board.Type;
import me.harry.iam.presentation.dto.PostDTO;
import me.harry.iam.presentation.exception.ResponseException;
import me.harry.iam.presentation.exception.e4xx.NotFoundException;
import me.harry.iam.presentation.response.OkResponse;
import me.harry.iam.presentation.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Page<PostResponse> readAllPost(@RequestParam Type type, @PageableDefault(size = 20, direction = Sort.Direction.ASC) Pageable pageable) throws ResponseException {
        if (type == null) {
            throw NotFoundException.TYPE.get();
        }
        return boardService.readPost(type, pageable).map(PostResponse::new);
    }

    @GetMapping("post/{id}")
    public PostResponse readPost(@PathVariable Long id) throws ResponseException {
        return new PostResponse(boardService.readPost(id));
    }

    @PatchMapping("post/{id}")
    public PostResponse editPost(@PathVariable Long id, @RequestBody @Valid PostDTO postDTO) throws ResponseException {
        return new PostResponse(boardService.editPost(id, postDTO));
    }

    @DeleteMapping("post/{id}")
    public ResponseEntity<OkResponse> deletePost(@PathVariable Long id) {
        boardService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
