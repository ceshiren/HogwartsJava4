/**
 * projectName: WeChatWorkApiTest
 * fileName: Demo_01_base.java
 * packageName: com.wechat.department
 * date: 2020-12-19 3:15 下午
 */
package com.ceshiren.hogwarts.service.wechat.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * 1.基础脚本，执行了创建，修改，查询
 */

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Demo_01_base
 * @packageName: com.wechat.department
 * @description: department show demo
 * @data: 2020-12-19 3:15 下午
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Demo_01_base {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);

    static String accessToken;
    static String departmentId;
    @BeforeAll
    public static  void getAccessToken(){
        accessToken = given().log().all()
                .when()
                .param("corpid","ww5ef451bf006ec894")
                .param("corpsecret","EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .extract().response().path("access_token");
    }
    @DisplayName("创建部门")
    @Order(1)
    @Test
    void createDepartment(){
        String body = "{\n" +
                "   \"name\": \"广州研发中心1\",\n" +
                "   \"name_en\": \"RDGZ1\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1,\n" +
                "}\n";
        Response response = given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken)
                .then().log().all()
                .extract()
                .response();
        departmentId = response.path("id").toString();
    }
    @DisplayName("修改部门")
    @Test
    @Order(2)
    void updateDepartment() {

        String body ="{\n" +
                "   \"id\": "+departmentId+",\n" +
                "   \"name\": \"根部门ID1\",\n" +
                "   \"name_en\": \"ROOT1\",\n" +
                "   \"order\": 1\n" +
                "}\n";
        Response response=given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken+"")
                .then()
                .log().body()
                .extract().response();
        assertEquals("0",response.path("errcode").toString());

    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    void listDepartment() {

        Response response =given().log().all()
                .when()
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0",response.path("errcode").toString());
        assertEquals(departmentId,response.path("department.id[0]").toString());

    }
    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment() {
        Response response = given().log().all()
                .contentType("application/json")
                .param("access_token",accessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract().response()
                ;
        assertEquals("0",response.path("errcode").toString());

    }
}