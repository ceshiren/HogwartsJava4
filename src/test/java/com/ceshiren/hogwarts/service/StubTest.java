package com.ceshiren.hogwarts.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class StubTest {
    static WireMockServer wireMockServer;
    @BeforeAll
    public static void  beforeAll(){
        wireMockServer = new WireMockServer(wireMockConfig().port(8089));

        wireMockServer.start();

        configureFor("localhost",8089);

        System.out.println("mock server start");
    }


    @Test
    void stubTest() throws Exception {
        stubFor(get(urlEqualTo("/user/name"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>naruto</response>")));
        Thread.sleep(500000);

    }


    @Test
    void easy_Mock(){
        try {


            stubFor(get(urlEqualTo("/user/name"))
                    .withHeader("Accept", equalTo("text/xml"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>naruto</response>")));
            Thread.sleep(5000);

            reset();

            stubFor(get(urlEqualTo("/user/name"))
                    .withHeader("Accept", equalTo("text/xml"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "text/xml")
                            .withBody("<response>hogwarts</response>")));
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Test
    void mockTest() throws Exception {

        stubFor(get(urlMatching(".*"))
                .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));


        stubFor(get(urlMatching("/categories_and_latest"))
                .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get(com.ceshiren.hogwarts.service.StubTest.class.getResource("/mock.json").getPath())))));
        Thread.sleep(500000);


    }
}
