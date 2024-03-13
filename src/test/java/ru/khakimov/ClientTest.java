package ru.khakimov;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.khakimov.dto.request.PostRequest;
import ru.khakimov.dto.response.PostResponse;
import ru.khakimov.service.proxy.JsonPlaceHolderPostsProxy;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class)
public class ClientTest {
    @Autowired
    JsonPlaceHolderPostsProxy postsProxy;
    private static WireMockServer wireMockServer;

    @BeforeAll
    static void init() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
    }

    @AfterAll
    static void destroy() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("get pos by id")
    void testGetPostById() {
        wireMockServer.stubFor(
                get(urlEqualTo("api/test/posts/1"))
                        .willReturn(
                                aResponse()
                                        .withHeader("Content-type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(
                                                """
                                                        {
                                                             "userId": 1,
                                                             "id": 1,
                                                             "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                                                             "body": "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                                                         }
                                                        """
                                        )
                        )
        );
        PostResponse expected = new PostResponse(
                1L, 1L,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        );
        assertThat(postsProxy.getPostById(1L).block()).isEqualTo(expected);
    }

    @Test
    @DisplayName("get pos by id")
    void testCreatePost() {
        wireMockServer.stubFor(
                get(urlEqualTo("api/test/posts"))
                        .willReturn(
                                aResponse()
                                        .withHeader("Content-type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(
                                                """
                                                        {
                                                             "userId": 1,
                                                             "title": "title",
                                                             "body": "body"
                                                         }
                                                        """
                                        )
                        )
        );
        PostRequest request = new PostRequest(1L, "title", "body");
        PostResponse expected = new PostResponse(
                1L, 1L,
                "title",
                "body"
        );
        assertThat(postsProxy.createPost(request).block()).isEqualTo(expected);
    }
}
