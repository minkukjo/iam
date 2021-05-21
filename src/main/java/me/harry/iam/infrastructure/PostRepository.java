package me.harry.iam.infrastructure;

import me.harry.iam.domain.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
