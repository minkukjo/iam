package me.harry.iam.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.harry.iam.AbstractMvcTest;
import me.harry.iam.domain.board.Post;
import me.harry.iam.domain.board.QPost;
import me.harry.iam.presentation.dto.PostDTO;
import me.harry.iam.utils.JacksonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BoardTest extends AbstractMvcTest {

    @Test
    @DisplayName("게시글 작성 테스트")
    public void create() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .title("오늘도 공부")
                .content("룰랄라")
                .build();
        String data = JacksonUtil.toJson(postDTO);

        this.mockMvc
                .perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.title").exists());
    }

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("Query DSL 테스트")
    @Transactional
    public void queryDSL() {
        Post build = Post.builder()
                .title("hello")
                .content("my")
                .build();

        em.persist(build);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QPost post = QPost.post;

        Post post1 = query
                .selectFrom(post)
                .fetchOne();

        assertEquals(post1, build);

    }
}
