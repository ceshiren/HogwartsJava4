package com.ceshiren.hogwarts.service;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.junit.jupiter.api.Test;
import static spark.Spark.*;


import java.io.IOException;

public class BMPTest {
    @Test
    public void bmp() throws IOException {




        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(8080);
        int port = proxy.getPort(); // get the JVM-assigned port

        // Selenium or HTTP client configuration goes here

        proxy.addResponseFilter((response, contents, messageInfo) -> {
            if (messageInfo.getOriginalUrl().contains(".json")) {
                //todo: json -> hashmap -> rescue -> hashmap -> json
                String contentNew = contents.getTextContents().replaceAll(":\"[^\"]*\"", "null");
                contents.setTextContents(contentNew);
//                contents.setTextContents("This message body will appear in all responses!");
            }
        });

        proxy.addRequestFilter(((request, contents, messageInfo) -> {
            request.setUri("/");
            return null;
        }));

        System.in.read();
    }

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
        get("/proxy", (req, res) -> {
            BrowserMobProxy proxy = new BrowserMobProxyServer();
            proxy.start(req.port());
            return null;
        });
    }
}
