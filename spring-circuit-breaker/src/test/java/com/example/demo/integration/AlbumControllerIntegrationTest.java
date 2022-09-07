package com.example.demo.integration;

import com.example.demo.model.Album;
import com.example.demo.wiremock.TypiSetUpMock;
import com.example.demo.wiremock.WireMockConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StreamUtils.copyToString;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {WireMockConfig.class})
@ActiveProfiles("test")
public class AlbumControllerIntegrationTest {

    private final String GET_ALBUMS_PATH = "/v1/albums";
    private final String EXPECTED_ALBUMS_LIST_PATH = "test-data/album-list.json";

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;
    @Autowired private WireMockServer mockTypiCodeServer;

    @Test
    void givenGetAlbumsFromTypiInternalError_whenGetAlbums_thenReturnAlbumsFromLocalService()
        throws Exception {
        // given
        TypiSetUpMock.setupGetAlbumTypiInternalError(mockTypiCodeServer);

        // when
        var result = mockMvc
            .perform(
                get(GET_ALBUMS_PATH))
            .andExpect(status().isOk()).andReturn();

        var content = result.getResponse().getContentAsString();
        List<Album> actual = deserializeResponseContent(content);
        List<Album> expected = deserializeResponseContent(getFileContentAsString(EXPECTED_ALBUMS_LIST_PATH));

        // then
        assertNotNull(actual);
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.size(), actual.size());
            assertEquals(expected.get(i).getId(), actual.get(i).getId());
            assertEquals(expected.get(i).getTitle(), actual.get(i).getTitle());
            assertEquals(expected.get(i).getUserId(), actual.get(i).getUserId());
        }
    }

    private List<Album> deserializeResponseContent(String content) throws JsonProcessingException {
        mapper.findAndRegisterModules();
        return mapper.readValue(content, new TypeReference<>() {});
    }

    private String getFileContentAsString(String path) throws IOException {
        return copyToString(AlbumControllerIntegrationTest.class.getClassLoader().getResourceAsStream(path),
            Charset.defaultCharset());
    }
}
