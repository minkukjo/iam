package me.harry.iam.application.board;

import me.harry.iam.domain.board.Post;
import me.harry.iam.domain.board.Type;
import me.harry.iam.infrastructure.PostRepository;
import me.harry.iam.infrastructure.aop.Logging;
import me.harry.iam.presentation.dto.PostDTO;
import me.harry.iam.presentation.exception.ResponseException;
import me.harry.iam.presentation.exception.e4xx.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Post writePost(PostDTO postDTO) {
        Post post = Post.of(postDTO.getTitle(), postDTO.getContent(), postDTO.getType());
        return postRepository.save(post);
    }


    @Transactional(readOnly = true)
    public Page<Post> readPost(Type type, Pageable pageable) {
        return postRepository.findAllByType(type, pageable);
    }

    @Logging
    @Transactional(readOnly = true)
    public Post readPost(Long id) throws ResponseException {
        return postRepository.findById(id).orElseThrow(NotFoundException.POST);
    }

    @Transactional
    public Post editPost(Long id, PostDTO postDTO) throws ResponseException {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException.POST);
        post.update(postDTO.getTitle(), postDTO.getContent(), postDTO.getType());
        return post;
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
