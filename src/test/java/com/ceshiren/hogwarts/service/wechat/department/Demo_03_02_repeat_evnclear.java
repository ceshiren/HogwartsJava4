package com.ceshiren.hogwarts.service.wechat.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1、基础脚本，分别执行了，创建、修改、查询、删除接口并进行了校验
 * 2、进行了优化，方法间进行解耦，每个方法可以独立行
 * 3、进行了优化，使用时间戳命名法避免入参重复造成的报错。
 * 4、进行了优化，每次方法执行前后都对历史数据进行清理，确保每次执行脚本数据环境一致。
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_03_02_repeat_evnclear {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);
    static String accessToken;
    static String departmentId;

    @BeforeAll
    public static void getAccessToken() throws Exception {
        accessToken=given()
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
        logger.info(accessToken);
    }
    @AfterEach
    @BeforeEach
    void clearDepartment(){
        Response listResponse =given().log().all()
                .when()
                .param("id",1)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
        for(int departmentId : departmentIdList){
            if(1==departmentId){
                continue;
            }
            Response DelResponse = given().log().all()
                    .contentType("application/json")
                    .param("access_token",accessToken)
                    .param("id",departmentId)
                    .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                    .then()
                    .log().body()
                    .extract().response()
                    ;
        }
    }
    @DisplayName("创建部门")
    @Test
    @Order(1)
    void createDepartment() {
        String body ="{\n" +
                "   \"name\": \"子部门ID16\",\n" +
                "   \"name_en\": \"child16\",\n" +
                "   \"parentid\": 1}";
        final Response response=given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken+"")
                .then()
                .log().body()
                .extract()
                .response()
                ;
        departmentId=response.path("id")!=null ? response.path("id").toString():null;
        assertEquals("0",response.path("errcode").toString());

    }
    @DisplayName("修改部门")
    @Test
    @Order(2)
    void updateDepartment() {
        String creatBody ="{\n" +
                "   \"name\": \"子部门ID16\",\n" +
                "   \"name_en\": \"child16\",\n" +
                "   \"parentid\": 1}";
        final Response creatResponse=given().log().all()
                .contentType("application/json")
                .body(creatBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken+"")
                .then()
                .log().body()
                .extract()
                .response()
                ;
        String departmentId=creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;
        String updateBody ="{\n" +
                "   \"id\": "+departmentId+",\n" +
                "   \"name\": \"根部门ID1\",\n" +
                "   \"name_en\": \"ROOT1\",\n" +
                "   \"order\": 1\n" +
                "}\n";
        Response updateResponse=given().log().all()
                .contentType("application/json")
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken+"")
                .then()
                .log().body()
                .extract().response();
        assertEquals("0",updateResponse.path("errcode").toString());

    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    void listDepartment() {
        String creatBody ="{\n" +
                "   \"name\": \"子部门ID16\",\n" +
                "   \"name_en\": \"child16\",\n" +
                "   \"parentid\": 1}";
        final Response creatResponse=given().log().all()
                .contentType("application/json")
                .body(creatBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken+"")
                .then()
                .log().body()
                .extract()
                .response()
                ;
        String departmentId=creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;
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
        String creatBody ="{\n" +
                "   \"name\": \"子部门ID16\",\n" +
                "   \"name_en\": \"child16\",\n" +
                "   \"parentid\": 1}";
        final Response creatResponse=given().log().all()
                .contentType("application/json")
                .body(creatBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken+"")
                .then()
                .log().body()
                .extract()
                .response()
                ;
        String departmentId=creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;
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
