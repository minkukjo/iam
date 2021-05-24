package me.harry.iam.application.board;

import lombok.AllArgsConstructor;
import me.harry.iam.domain.board.Post;
import me.harry.iam.infrastructure.PostRepository;
import me.harry.iam.presentation.dto.PostDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BoardService {
    private final PostRepository postRepository;

    @Transactional
    public Post writePost(PostDTO postDTO) {
        Post post = Post.builder()
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .build();
        return postRepository.save(post);
    }

    public Post readPost() {
        return null;
    }

    public Post editPost() {
        return null;
    }

    public void deletePost() {

    }
}
