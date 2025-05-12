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
import se.callistaenterprise.boxproblem.api.dto.RowDto;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void findSuitableBox_validArticles_returnsCorrectBoxId(List<RowDto> rows, String expectedBoxId) throws Exception {
        String request = objectMapper.writeValueAsString(rows);

        mockMvc.perform(post("/api/boxes/suitable")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(expectedBoxId));
    }

    @Test
    void findSuitableBox_nonExistentArticleId_returnsBadRequest() throws Exception {
        String request = "[{\"quantity\":1,\"id\":99}]";

        mockMvc.perform(post("/api/boxes/suitable")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Article with ID=99 does not exist"));
    }
}