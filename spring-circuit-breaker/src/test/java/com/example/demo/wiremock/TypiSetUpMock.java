package com.example.demo.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class TypiSetUpMock {

    private static final String MOCK_ALBUM_PATH_TYPI = "/albums";
    private static final String CONTENT_TYPE = "Content-Type";

    public static void setupGetAlbumTypiInternalError(WireMockServer mockService) {
        mockService.stubFor(
            WireMock.get(WireMock.urlEqualTo(MOCK_ALBUM_PATH_TYPI))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));
    }

    public static void setupGetAlbumTypiSuccessfully(WireMockServer mockService)
        throws IOException {
        mockService.stubFor(
            WireMock.get(WireMock.urlEqualTo(MOCK_ALBUM_PATH_TYPI))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                            copyToString(
                                TypiSetUpMock.class
                                    .getClassLoader()
                                    .getResourceAsStream(
                                        "test-data/album-list.json"),
                                defaultCharset()))));
    }
}
