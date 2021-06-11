package me.harry.iam.application.board;

import lombok.AllArgsConstructor;
import me.harry.iam.domain.board.Post;
import me.harry.iam.infrastructure.PostRepository;
import me.harry.iam.presentation.dto.PostDTO;
import me.harry.iam.presentation.exception.ResponseException;
import me.harry.iam.presentation.exception.e4xx.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<Post> readPost(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Post readPost(Long id) throws ResponseException {
        return postRepository.findById(id).orElseThrow(NotFoundException.POST);
    }

    @Transactional
    public Post editPost(Long id, PostDTO postDTO) throws ResponseException {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException.POST);
        post.update(postDTO.getTitle(), postDTO.getContent());
        return post;
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
