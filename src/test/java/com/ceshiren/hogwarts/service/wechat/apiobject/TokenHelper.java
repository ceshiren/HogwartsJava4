/**
 * projectName: WeChatWorkApiTest
 * fileName: TokenHelper.java
 * packageName: com.wechat.apiobject
 * date: 2020-12-19 4:24 下午
 */
package com.ceshiren.hogwarts.service.wechat.apiobject;

import static io.restassured.RestAssured.given;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: TokenHelper
 * @packageName: com.wechat.apiobject
 * @description: tokenhelper
 * @data: 2020-12-19 4:24 下午
 **/
public class TokenHelper {
    public static String getAccessToken(){
        String accessToken=given()
//                    .log().all()
                .when()
                .param("corpid","ww5ef451bf006ec894")
                .param("corpsecret","EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
//                    .log().body()
                .extract()
                .response()
                .path("access_token");
        return accessToken;
    }

}