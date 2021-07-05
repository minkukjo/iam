package me.harry.iam.infrastructure;

import me.harry.iam.domain.board.Post;
import me.harry.iam.domain.board.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByType(Type type, Pageable pageable);
}
