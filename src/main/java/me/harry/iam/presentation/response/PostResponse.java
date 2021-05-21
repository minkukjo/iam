package me.harry.iam.presentation.response;

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

    public PostResponse(Post post) {
        this.id = post.getId();
        this.writer = post.getAuthor();
        this.comments = post.getComments();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
