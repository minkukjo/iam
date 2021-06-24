package me.harry.iam.presentation.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import me.harry.iam.domain.board.Comment;
import me.harry.iam.domain.board.Post;
import me.harry.iam.domain.user.User;

@Builder
@Getter
public class PostResponse extends OkResponse {
    private final Long id;
    private final User writer;
    private final List<Comment> comments;
    private final String title;
    private final String content;

    private final Long views;

    private final Long likes;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public PostResponse(Post post) {
        super();
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
