package me.harry.iam.application.board;

import me.harry.iam.domain.board.Post;
import me.harry.iam.presentation.dto.PostDTO;

public interface BoardService {
    Post writePost(PostDTO postDTO);
    Post readPost();
    Post editPost();
    void deletePost();
}
