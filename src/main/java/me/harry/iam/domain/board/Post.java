package me.harry.iam.domain.board;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.harry.iam.domain.BaseEntity;
import me.harry.iam.domain.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Comment> comments = new ArrayList<>();

    @Column(length = 128, nullable = false)
    private String title;

    @Column(length = 512)
    private String content;

    @Column(nullable = false)
    @Convert(converter = Type.TypeJpaConverter.class)
    private Type type;

    @Column
    private Long views;

    @Column
    private Long likes;

    private Post(String title, String content, Type type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public static Post of(String title, String content, Type type) {
        return new Post(title, content, type);
    }

    public void update(String title, String content, Type type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }
}
