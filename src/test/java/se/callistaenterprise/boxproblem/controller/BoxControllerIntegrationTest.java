package se.callistaenterprise.boxproblem.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.callistaenterprise.boxproblem.dto.BoxRequest;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class BoxControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("se.callistaenterprise.boxproblem.common.TestUtil#sampleInput")
    void shouldSelectAppropriateBoxForGivenArticles(Map<Integer, Integer> articles, String expectedBoxId) throws Exception {
        BoxRequest boxRequest = new BoxRequest(articles);
        String requestJson = objectMapper.writeValueAsString(boxRequest);

        mockMvc.perform(post("/api/box/find")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(expectedBoxId)).andDo(print());

    }

    @Test
    void shouldRejectRequestWithNonExistentArticleId() throws Exception {
        String requestJson = "{\"articles\":{\"999\":5}}"; // Invalid Article ID

        mockMvc.perform(post("/api/box/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Article with ID=999 does not exist"));
    }

    @Test
    void shouldRejectRequestWithEmptyArticles() throws Exception {
        mockMvc.perform(post("/api/box/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"articles\":{}}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Articles must not be empty"));
    }

    @Test
    void shouldRejectEmptyRequestBody() throws Exception {
        mockMvc.perform(post("/api/box/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"))
                .andExpect(jsonPath("$.message").value("Failed to parse incoming request"));
    }

    @Test
    void shouldRejectMalformedJsonRequest() throws Exception {
        mockMvc.perform(post("/api/box/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"articles\":{} invalid json")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"))
                .andExpect(jsonPath("$.message").value("Failed to parse incoming request"));
    }

    @Test
    void shouldRejectRequestWithMissingArticlesField() throws Exception {
        mockMvc.perform(post("/api/box/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Request"))
                .andExpect(jsonPath("$.message").value("Articles must not be null"));
    }

}