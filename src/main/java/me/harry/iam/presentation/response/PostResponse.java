package me.harry.iam.presentation.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.harry.iam.domain.board.Comment;
import me.harry.iam.domain.board.Post;
import me.harry.iam.domain.user.User;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private User writer;
    private List<Comment> comments;
    private String title;
    private String content;

    private Long views;

    private Long likes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.writer = post.getAuthor();
        this.comments = post.getComments();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.views = post.getViews();
        this.likes = post.getLikes();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}
