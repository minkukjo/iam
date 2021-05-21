package me.harry.iam.application.board;

import lombok.AllArgsConstructor;
import me.harry.iam.domain.board.Post;
import me.harry.iam.infrastructure.PostRepository;
import me.harry.iam.presentation.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final PostRepository postRepository;

    @Override
    public Post writePost(PostDTO postDTO) {
        Post post = Post.builder()
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .build();
        return postRepository.save(post);
    }

    @Override
    public Post readPost() {
        return null;
    }

    @Override
    public Post editPost() {
        return null;
    }

    @Override
    public void deletePost() {

    }
}
