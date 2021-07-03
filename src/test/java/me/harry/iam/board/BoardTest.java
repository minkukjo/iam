package me.harry.iam.board;

import me.harry.iam.AbstractMvcTest;
import me.harry.iam.domain.board.Post;
import me.harry.iam.domain.board.Type;
import me.harry.iam.infrastructure.PostRepository;
import me.harry.iam.presentation.dto.PostDTO;
import me.harry.iam.utils.JacksonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BoardTest extends AbstractMvcTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("게시글 작성 테스트")
    public void write() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .title("오늘도 공부")
                .content("룰랄라")
                .type(Type.COMMUNITY)
                .build();
        String data = JacksonUtil.toJson(postDTO);

        this.mockMvc
                .perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("오늘도 공부"));
    }

    @Test
    @DisplayName("게시글 조회 테스트")
    public void readOnePost() throws Exception {
        write();

        this.mockMvc
                .perform(get("/api/post/{id}", 1L))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.title").exists());
    }

    @Test
    @DisplayName("게시글 조회 실패 예외 테스트")
    public void readOnePostFail() throws Exception {
        write();

        this.mockMvc
                .perform(get("/api/post/{id}", 9999L))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @DisplayName("게시글 업데이트")
    public void editPost() throws Exception {
        write();

        PostDTO postDTO = PostDTO.builder()
                .title("The Test")
                .content("I'm so serious")
                .type(Type.COMMUNITY)
                .build();
        String data = JacksonUtil.toJson(postDTO);

        this.mockMvc
                .perform(patch("/api/post/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.title").value("The Test"))
                .andExpect(jsonPath("$.content").value("I'm so serious"));
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    public void deletePost() throws Exception {
        write();

        this.mockMvc
                .perform(delete("/api/post/{id}", 1L))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()));

        Post post = postRepository.findById(1L).orElse(null);
        assertNull(post);
    }
}
